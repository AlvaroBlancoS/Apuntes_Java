# Guía rápida del token

> **Idea principal:** este proyecto usa un token *Bearer* guardado en base de datos. No es un JWT firmado. Tras iniciar sesión, el cliente recibe una cadena aleatoria y la envía en cada petición protegida.

## Ruta de 60 segundos

Si solo lees una cosa, sigue estas cuatro flechas:

```mermaid
flowchart LR
    A[1. POST /api/tokens/login] --> B[2. TokenService comprueba usuario y contraseña]
    B --> C[3. Guarda un token válido 2 horas]
    C --> D[4. Cliente envía Authorization: Bearer token]
    D --> E[5. BearerTokenFilter identifica usuario y rol]
    E --> F[6. Mail o User autorizan la operación]
```

**Regla para no perderse:** primero se crea el token en `Token`; después se valida en `CommonBookstore`; por último `User` o `Mail` decide si el rol puede hacer la acción.

---

## Mapa del proyecto

| Módulo | Puerto | Responsabilidad | Qué recordar |
| --- | ---: | --- | --- |
| `CommonBookstore` | — | Código compartido, entidades y filtro | Aquí vive la validación reutilizable del Bearer. |
| `Token` | `8082` | Inicio de sesión y emisión del token | Crea y persiste el token. |
| `User` | `8081` | Gestión de usuarios | Exige token y aplica roles. |
| `Mail` | `8080` | Gestión de correos | Exige token y aplica roles. |

---

# 0. Las clases que encienden los proyectos

## Idea en 20 segundos

Cada clase `*Application` es el **botón de arranque** de su módulo: Spring Boot empieza desde su método `main`. Las anotaciones que están encima indican qué código compartido debe encontrar Spring.

```mermaid
flowchart LR
    M[Arranca Mail] --> E[Crea los correos base]
    E --> U[Arranca User]
    U --> R[Crea user y admin]
    R --> T[Arranca Token y realiza login]
```

**Orden práctico de primera ejecución:** inicia `Mail`, después `User` y, por último, `Token`. `UserApplication` necesita que ya existan los correos `user@user.com` y `admin@admin.com`.

## `TokenApplication`: prepara el módulo que emite tokens

Archivo: `Token/src/main/java/springboot/token/token/TokenApplication.java`.

```java
@SpringBootApplication
@EntityScan(basePackages = {
    "springboot.entity", "springboot.security.entity", "springboot.token.token.entity"
})
@EnableJpaRepositories(basePackages = {
    "springboot.repository", "springboot.security.repository", "springboot.token.token.repository"
})
@ComponentScan(basePackages = { "springboot.token.token", "springboot.util.exception" })
public class TokenApplication {
    public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class, args);
    }
}
```

- `@SpringBootApplication`: activa Spring Boot y arranca el servidor.
- `@EntityScan`: encuentra entidades como `User`, `Mail` y `Token` para poder leerlas y guardarlas en la base de datos.
- `@EnableJpaRepositories`: encuentra repositorios, incluido `TokenRepository`.
- `@ComponentScan`: encuentra controladores, servicios, configuración y manejo de errores propios de Token.

**Qué recordar:** Token puede consultar usuarios y correos porque escanea el código compartido, pero su tarea principal es crear el ticket de acceso.

## `MailApplication`: crea los correos de ejemplo y carga el filtro

Archivo: `Mail/src/main/java/springboot/token/mail/MailApplication.java`.

```java
@SpringBootApplication
@EntityScan(basePackages = { "springboot.entity", "springboot.security.entity" })
@EnableJpaRepositories(basePackages = { "springboot.repository", "springboot.security.repository" })
@Import({ MailMapper.class, BearerTokenFilter.class, GlobalExceptionHandler.class })
public class MailApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }
}
```

`@Import` incorpora clases de `CommonBookstore` que este proyecto necesita: el conversor de correos, el filtro que reconoce `Bearer` y el manejo común de errores.

Al terminar de arrancar, este bloque crea datos iniciales si aún no están:

```java
@Bean
CommandLineRunner commandLineRunner(EmailRepository emailRepository) {
    return args -> {
        if (!emailRepository.findByMail("user@user.com").isPresent()
                && !emailRepository.findByMail("admin@admin.com").isPresent()) {
            emailRepository.save(new Mail(null, "user@user.com", "User email accesing token"));
            emailRepository.save(new Mail(null, "admin@admin.com", "Admin email accessing token"));
        }
    };
}
```

**Resultado:** quedan preparados los dos correos que utilizará el proyecto User.

## `UserApplication`: crea los usuarios de ejemplo y sus roles

Archivo: `User/src/main/java/springboot/token/user/UserApplication.java`.

```java
@SpringBootApplication
@EntityScan(basePackages = { "springboot.entity", "springboot.security.entity" })
@EnableJpaRepositories(basePackages = { "springboot.repository", "springboot.security.repository" })
@ComponentScan(basePackages = { "springboot.token.user", "springboot.mapper" })
@Import({ BearerTokenFilter.class, GlobalExceptionHandler.class })
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
```

La diferencia importante con Mail es que aquí `@ComponentScan` descubre los controladores, servicios y mappers de usuarios. `@Import` añade el filtro Bearer compartido y el gestor de errores.

Su `CommandLineRunner` busca primero los dos correos creados por Mail y, si no existen ambos usuarios, crea estas cuentas:

```java
userRepository.save(User.builder()
    .name("user").password("user").mailId(mailId)
    .roleType(RoleType.USER).build());

userRepository.save(User.builder()
    .name("admin").password("admin").mailId(mailIdAdmin)
    .roleType(RoleType.ADMIN).build());
```

- `user` tiene `ROLE_USER`: puede consultar recursos.
- `admin` tiene `ROLE_ADMIN`: también puede crear, editar y borrar.

> **Atención:** estas contraseñas de ejemplo están escritas en texto plano para arrancar la demo. No deben usarse así en una aplicación real.

---

# 1. CommonBookstore: la caja de piezas compartidas

Ruta base: [`CommonBookstore/src/main/java/springboot/security`.](https://github.com/AlvaroBlancoS/Apuntes_Java/tree/spring_boot/token/CommonBookstore/src/main/java/springboot/security)

## `Token`: qué se guarda

Archivo: [`security/entity/Token.java`.](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/security/entity/Token.java#L31)

Representa una fila de la tabla `token.tokens`. Contiene:

- `id`: identificador técnico UUID.
- `creationDate`: momento de creación.
- `expirationDate`: límite de validez.
- `userId`: usuario al que pertenece.
- `token`: la cadena secreta que recibe el cliente; es única y puede tener hasta 512 caracteres.
- `tokenType`: tipo del encabezado, actualmente `BEARER`.

**Imagen mental:** es un ticket de acceso con nombre, fecha de caducidad y una clave difícil de adivinar.

```java
@Entity
@Table(name = "tokens", schema = "token")
public class Token {
    private UUID id;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private UUID userId;
    private String token;
    private TokenType tokenType;
}
```

No es necesario memorizar las anotaciones JPA ahora: señalan que la clase se guarda en la base de datos. Lo importante es la relación **token → usuario → fecha de caducidad**.

## `TokenType`: el prefijo esperado

Archivo: [`security/entity/TokenType.java`.](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/security/entity/TokenType.java)

El `enum` solo define `BEARER`. Por eso el cliente debe enviar exactamente este formato:

```http
Authorization: Bearer TU_TOKEN
```

`Bearer` no es parte de la clave guardada: es el indicador del protocolo que el filtro reconoce.

```java
public enum TokenType {
    BEARER
}
```

Esta clase deja preparado el proyecto para admitir otros tipos en el futuro, aunque por ahora solo se usa uno.

## `TokenRepository`: hablar con la tabla de tokens

Archivo: [`security/repository/TokenRepository.java`.](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/security/repository/TokenRepository.java)

Hereda operaciones estándar de `JpaRepository<Token, UUID>` y añade dos consultas importantes:

```java
findByToken(String token)
deleteAllByExpirationDateBefore(LocalDateTime dateTime)
```

- La primera localiza el ticket recibido en el encabezado.
- La segunda permite borrar tokens ya caducados. [`TokenService`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/Token/src/main/java/springboot/token/token/service/TokenService.java) tiene el método [`deleteExpiredTokens()`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/Token/src/main/java/springboot/token/token/service/TokenService.java#L131), aunque en el código actual no hay una tarea programada que lo invoque automáticamente.

```java
public interface TokenRepository extends JpaRepository<Token, UUID> {
    Optional<Token> findByToken(String token);
    void deleteAllByExpirationDateBefore(LocalDateTime dateTime);
}
```

## `BearerTokenFilter`: el portero

Archivo: [`security/BearerTokenFilter.java`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/security/BearerTokenFilter.java#L25).

Se ejecuta una vez por petición porque extiende `OncePerRequestFilter`.

```mermaid
flowchart TD
    A[Petición a User o Mail] --> B{¿Hay Authorization: Bearer ...?}
    B -- No --> C[Continúa sin identidad]
    B -- Sí --> D[Extrae la cadena tras 'Bearer ']
    D --> E{¿Existe en TokenRepository?}
    E -- No --> C
    E -- Sí --> F{¿No ha caducado?}
    F -- No --> C
    F -- Sí --> G[Busca al usuario mediante userId]
    G --> H[Introduce ID y ROLE_USER o ROLE_ADMIN en SecurityContext]
    H --> I[SecurityConfig autoriza o rechaza]
```

Detalles que importan:

1. Si falta el encabezado, está mal formado, no existe el token, ha expirado o no existe su usuario, el filtro **no autentica**.
2. Si todo es correcto, crea un [`UsernamePasswordAuthenticationToken`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/security/BearerTokenFilter.java#L55) con el ID del usuario y la autoridad `ROLE_` + su `RoleType`.
3. El filtro no responde directamente con un error: deja continuar la petición. Después, Spring Security deniega las rutas que requieren autenticación o un rol.

El núcleo del código es este:

```java
String authHeader = request.getHeader("Authorization");
if (authHeader == null || !authHeader.startsWith("Bearer ")) {
    filterChain.doFilter(request, response);
    return;
}

Token token = tokenRepository.findByToken(authHeader.substring(7)).orElse(null);
if (token != null && token.getExpirationDate().isAfter(LocalDateTime.now())) {
    User user = userRepository.findById(token.getUserId()).orElse(null);
    // Si existe, guarda su ID y ROLE_<rol> en SecurityContextHolder.
}
```

---

# 2. Proyecto Token: crear el ticket

Ruta: [`Token/src/main/java/springboot/token/token`.](https://github.com/AlvaroBlancoS/Apuntes_Java/tree/spring_boot/token/Token/src/main/java/springboot/token/token)

## El inicio de sesión, paso a paso

El endpoint es [`POST /api/tokens/login`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/Token/src/main/java/springboot/token/token/controller/TokenController.java#L25) en `TokenController`.

```json
{
  "userName": "usuario",
  "password": "contraseña"
}
```

`TokenService.login()` hace esta secuencia:

1. Busca el usuario por nombre.
2. Comprueba la contraseña.
3. Genera 48 bytes aleatorios con `SecureRandom`.
4. Los codifica en Base64 URL-safe sin relleno.
5. Guarda un `Token` de tipo `BEARER`, válido durante **2 horas**.
6. Devuelve un [`TokenResponseDto`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/security/dto/TokenResponseDto.java) con el valor del token y sus fechas.

Después, copia solo el campo `token` y úsalo en `Authorization` para llamar a `User` o `Mail`.

## `SecurityConfig`: qué permite Token

Archivo: [`Token/.../util/SecurityConfig.java`.](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/Token/src/main/java/springboot/token/token/util/SecurityConfig.java)

- Declara un `BCryptPasswordEncoder`, usado para comprobar contraseñas.
- Desactiva CSRF, apropiado para una API sin formularios con sesión.
- Permite sin autenticación el login y las rutas de Swagger.
- **También permite cualquier otra ruta** mediante `.anyRequest().permitAll()`.

En otras palabras: `Token` no usa el `BearerTokenFilter` para proteger sus rutas. Su trabajo es expedir tokens; la protección de recursos ocurre en `User` y `Mail`.

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/tokens/login", "/swagger-ui/**", "/v3/api-docs/**")
            .permitAll()
            .anyRequest().permitAll());
    return http.build();
}
```

> **Nota de seguridad para el futuro:** `isPasswordValid()` acepta tanto una contraseña BCrypt como una coincidencia de texto plano. Es útil para los usuarios semilla actuales, pero en producción convendría eliminar `rawPassword.equals(storedPassword)` y almacenar siempre hashes BCrypt.

## OpenAPI / Swagger en Token

El módulo incluye Springdoc y el controlador tiene anotaciones `@Operation`, por lo que Swagger puede descubrir el endpoint. **No existe una clase `OpenApiConfig` propia en `Token`**.

Por ello Token no declara en código el esquema global `bearerAuth` que sí existe en `User` y `Mail`. En Swagger, el login se puede probar directamente sin pulsar `Authorize`.

---

# 3. Proyecto User: proteger usuarios por rol

Rutas: [`User/src/main/java/springboot/token/user`](https://github.com/AlvaroBlancoS/Apuntes_Java/tree/spring_boot/token/User) y puerto `8081`.

## [`SecurityConfig`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/User/src/main/java/springboot/token/user/util/SecurityConfig.java#L31): reglas en una mirada

El filtro compartido se inserta antes de [`UsernamePasswordAuthenticationFilter`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/User/src/main/java/springboot/token/user/util/SecurityConfig.java#L54) y la aplicación es **stateless**: no guarda sesión en el servidor.

| Operación sobre `/api/users/**` | Rol necesario |
| --- | --- |
| `GET` | `USER` o `ADMIN` |
| `POST`, `PUT`, `DELETE` | Solo `ADMIN` |
| Swagger (`/swagger-ui/**`, `/v3/api-docs/**`) | Público |

**Traducción:** tener un token válido identifica al usuario; tener `ROLE_ADMIN` además permite modificar usuarios.

Este es el patrón relevante de la configuración:

```java
http.csrf(csrf -> csrf.disable())
    .sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("USER", "ADMIN")
        .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
        .anyRequest().authenticated())
    .addFilterBefore(bearerTokenFilter, UsernamePasswordAuthenticationFilter.class);
```

## `OpenApiConfig`: el botón Authorize de Swagger

Archivo: [`User/.../OpenApiConfig.java`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/User/src/main/java/springboot/token/user/OpenApiConfig.java#L16).

Configura título, versión y descripción de la documentación. También define un esquema HTTP llamado `bearerAuth` y lo aplica como requisito global.

En Swagger:

1. Llama antes a `POST http://localhost:8082/api/tokens/login`.
2. Copia el valor de `token` de la respuesta.
3. Abre Swagger de User y pulsa **Authorize**.
4. Pega el token (Swagger usa el esquema Bearer configurado).
5. Prueba los endpoints permitidos para tu rol.

Aunque se muestra `bearerFormat("JWT")`, el token real de este proyecto es opaco y está guardado en base de datos, no es un JWT. El texto de Swagger se puede ajustar más adelante a un formato neutral como `Bearer token` para evitar confusión.

```java
return new OpenAPI()
    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
    .components(new Components().addSecuritySchemes("bearerAuth",
        new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")));
```

La primera línea hace que Swagger solicite autenticación para las operaciones documentadas. Las siguientes describen cómo debe construir el encabezado HTTP.

---

# 4. Proyecto Mail: misma puerta, otros recursos

Ruta: [`Mail/src/main/java/springboot/token/mail`](https://github.com/AlvaroBlancoS/Apuntes_Java/tree/spring_boot/token/Mail/src/main/java/springboot/token/mail) y puerto `8080`.

## [`SecurityConfig`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/Mail/src/main/java/springboot/token/mail/SecurityConfig.java#L30)

Tiene el mismo patrón que User:

- Sin CSRF y sin sesión (`STATELESS`).
- Ejecuta `BearerTokenFilter` antes del filtro estándar de usuario/contraseña.
- Deja Swagger público.
- Para `/api/mail/**`, `GET` permite `USER` o `ADMIN`; `POST`, `PUT` y `DELETE` requieren `ADMIN`.

El código es equivalente al de User, cambiando solo la ruta:

```java
.requestMatchers(HttpMethod.GET, "/api/mail/**").hasAnyRole("USER", "ADMIN")
.requestMatchers(HttpMethod.POST, "/api/mail/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.PUT, "/api/mail/**").hasRole("ADMIN")
.requestMatchers(HttpMethod.DELETE, "/api/mail/**").hasRole("ADMIN")
.addFilterBefore(bearerTokenFilter, UsernamePasswordAuthenticationFilter.class);
```

## [`OpenApiConfig`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/Mail/src/main/java/springboot/token/mail/OpenApiConfig.java#L16)

Configura el título «Servicio de correos» y registra/aplica el esquema `bearerAuth`. El uso en Swagger es idéntico al de User: inicia sesión en Token, copia el valor y autoriza en Swagger de Mail.

La misma precisión aplica aquí: `bearerFormat("JWT")` es solo una etiqueta de documentación; el mecanismo real es un token opaco persistido en la tabla `tokens`.

Su configuración OpenAPI sigue el mismo esquema que la de User:

```java
new SecurityScheme()
    .type(SecurityScheme.Type.HTTP)
    .scheme("bearer")
    .bearerFormat("JWT")
```

---

# Recorrido completo para probarlo

## 1. Pide un token

```http
POST http://localhost:8082/api/tokens/login
Content-Type: application/json

{
  "userName": "user",
  "password": "user"
}
```

Los usuarios semilla se crean en el proyecto `User`; `user` tiene rol `USER` y `admin` tiene rol `ADMIN`.

## 2. Llama a un recurso con el token

```http
GET http://localhost:8081/api/users
Authorization: Bearer PEGA_AQUI_EL_CAMPO_TOKEN
```

O bien:

```http
GET http://localhost:8080/api/mail
Authorization: Bearer PEGA_AQUI_EL_CAMPO_TOKEN
```

## 3. Si recibes un error, revisa en este orden

- **401 o 403:** ¿enviaste `Authorization: Bearer ` con un espacio tras `Bearer`?
- **401 o 403:** ¿el token existe y no ha pasado su fecha `expirationDate`? Dura dos horas.
- **403 al crear, editar o borrar:** ¿el usuario del token es `ADMIN`?
- **Error al iniciar sesión:** ¿el usuario existe y la contraseña coincide?
- **Swagger no carga:** comprueba que el servicio correspondiente está arrancado; las rutas de documentación están permitidas por `SecurityConfig`.

## Mini resumen final

`Token` emite y guarda el ticket → `BearerTokenFilter` lo valida y asigna el rol → `User` y `Mail` aplican las reglas por método HTTP → OpenAPI permite enviar ese Bearer cómodamente desde Swagger.

# CODIGOS IMPORTANTES PARA EL DESARROLLO

## 1- COMMONBOOKSTORE

### Security
```java
@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    Optional<Token> findByToken(String token);

    void deleteAllByExpirationDateBefore(LocalDateTime dateTime);
}
```

```java
@Component
@RequiredArgsConstructor
public class BearerTokenFilter extends OncePerRequestFilter {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenValue = authHeader.substring(7);

        Token token = tokenRepository.findByToken(tokenValue)
                .orElse(null);

        if (token != null && token.getExpirationDate().isAfter(LocalDateTime.now())) {

            User user = userRepository.findById(token.getUserId())
                    .orElse(null);

            if (user != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user.getId(),
                                null,
                                List.of(new SimpleGrantedAuthority(
                                        "ROLE_" + user.getRoleType().name()
                                ))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
```

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tokens", schema = "token")
public class Token {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "uuid", strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_token_user"))
    private UUID userId;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false)
    private TokenType tokenType;
}
```

```java
public enum TokenType {
    BEARER
}
```

## 2- SERVICIOS DE TOKEN, MAIL Y USER.

### 1- Token SecurityConfig
```java
@Configuration
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/tokens/login",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
```
### 2- User SecurityConfig

```java
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

        private final BearerTokenFilter bearerTokenFilter;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui.html")
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/api/users/**")
                                                .hasAnyRole("USER", "ADMIN")
                                                
                                                .requestMatchers(HttpMethod.POST, "/api/users/**")
                                                .hasRole("ADMIN")
                                                
                                                .requestMatchers(HttpMethod.PUT, "/api/users/**")
                                                .hasRole("ADMIN")
                                                
                                                .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .addFilterBefore(
                                                bearerTokenFilter,
                                                UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
```

### 3- Mail SecurityConfig
```java
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final BearerTokenFilter bearerTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/mail/**")
                        .hasAnyRole("USER", "ADMIN")
                        
                        .requestMatchers(HttpMethod.POST, "/api/mail/**")
                        .hasRole("ADMIN")
                        
                        .requestMatchers(HttpMethod.PUT, "/api/mail/**")
                        .hasRole("ADMIN")
                        
                        .requestMatchers(HttpMethod.DELETE, "/api/mail/**")
                        .hasRole("ADMIN")
                        
                        .anyRequest().authenticated())
                .addFilterBefore(
                        bearerTokenFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```
### 4- User OpenApi
```java
@Configuration
public class OpenApiConfig {

      @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Servicio de usuarios")
                        .version("1.0")
                        .description("API para gestionar usuarios"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ));
    }

}
```
### 5- Mail OpenApi
```java
@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Servicio de correos")
                        .version("1.0")
                        .description("API para gestionar correos"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ));
    }
}
```
