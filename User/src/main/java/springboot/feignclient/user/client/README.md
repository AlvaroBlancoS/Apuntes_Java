# Feign Client entre `User` y `Mail`

Este paquete contiene la comunicación entre dos proyectos distintos:

- `User`: gestiona usuarios.
- `Mail`: gestiona correos electrónicos.

La idea es que el proyecto `User` no accede directamente a la base de datos del proyecto `Mail`, sino que consume sus endpoints HTTP mediante `Feign Client`.

## 1. Endpoint elegido en el otro proyecto

En el proyecto `Mail`, el controlador está en:

- `Mail/src/main/java/springboot/feignclient/mail/controller/MailController.java`

Los endpoints que se usan desde `User` son estos:

```java
@GetMapping("/{id}")
public MailDto getMailById(@PathVariable UUID id)
```

```java
@GetMapping("/name/{name}")
public MailDto getMailByName(@PathVariable @Valid String name)
```

Sus rutas completas son:

- `GET /api/mail/{id}`
- `GET /api/mail/name/{name}`

### Por qué escogí esos endpoints

Escogí esos endpoints porque `User` necesita recuperar información de `Mail` de dos formas:

- por `id`, cuando el usuario ya guarda el campo `mailId`
- por `name`, cuando quiero buscar un usuario a partir del correo electrónico

Son endpoints `GET` porque solo consultan información y no modifican datos.

## 2. Cliente Feign en el proyecto `User`

En `User`, el cliente Feign está en:

- `User/src/main/java/springboot/feignclient/user/client/MailFeignClient.java`

```java
@FeignClient(name = "mail-service", url = "http://localhost:8080")
public interface MailFeignClient {

    @GetMapping("/api/mail/{id}")
    MailDto getMailById(@PathVariable("id") UUID id);

    @GetMapping("/api/mail/name/{name}")
    MailDto getMailByName(@PathVariable("name") String name);
}
```

Esta interfaz actúa como un cliente HTTP declarativo:

- no escribimos `RestTemplate`
- no escribimos `WebClient`
- solo definimos la ruta y el tipo de dato esperado

Spring y OpenFeign se encargan de hacer la llamada al otro microservicio.

## 3. Papel de `MailDto`

El DTO usado en `User` está en:

- `User/src/main/java/springboot/feignclient/user/dto/MailDto.java`

```java
public class MailDto {
    private UUID id;
    private String mail;
    private String description;
}
```

`MailDto` es importante porque representa los datos que devuelve el proyecto `Mail`.

Feign recibe la respuesta JSON del endpoint remoto y la convierte en un objeto `MailDto`.

Por ejemplo, si `Mail` devuelve algo parecido a esto:

```json
{
  "id": "uuid-del-mail",
  "mail": "usuario@correo.com",
  "description": "correo principal"
}
```

Feign lo transforma automáticamente en un `MailDto`.

## 4. Uso dentro del `Mapper` de `User`

El punto más importante de la integración está en:

- `User/src/main/java/springboot/feignclient/user/mapper/Mapper.java`

### 4.1. Al crear un usuario

```java
public User convertToEntity(UserDto dto) {
    return User.builder()
            .name(dto.getName())
            .password(passwordEncoder.encode(dto.getPassword()))
            .mailId(mailFeignClient.getMailById(dto.getMailId()).getId())
            .build();
}
```

Aquí ocurre esto:

1. llega un `UserDto` con un `mailId`
2. el `Mapper` llama a `mailFeignClient.getMailById(dto.getMailId())`
3. esa llamada consulta el endpoint `GET /api/mail/{id}` del proyecto `Mail`
4. si el correo existe, recupera su `id`
5. ese `id` se guarda en la entidad `User`

### Por qué es útil

Así se valida que el `mailId` enviado realmente existe en el otro microservicio antes de guardar el usuario.

### 4.2. Al devolver un usuario

```java
public UserDto convertToDto(User entity) {
    return UserDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .mailDto(getMailById(entity.getMailId()))
            .build();
}
```

Aquí el `Mapper` vuelve a consultar `Mail`, pero ahora para enriquecer la respuesta.

En lugar de devolver solo el `mailId`, también devuelve el objeto `MailDto` completo.

### 4.3. Métodos auxiliares del `Mapper`

```java
public MailDto getMailById(UUID idMail)
```

```java
public MailDto getMailByName(String name)
```

Estos métodos encapsulan la llamada Feign y además controlan el error:

- si `Mail` responde con `404 Not Found`
- el `Mapper` transforma ese error en una `ResponseStatusException`

Esto evita propagar directamente la excepción de Feign al resto de capas.

## 5. Uso dentro del servicio `UserService`

La capa de servicio está en:

- `User/src/main/java/springboot/feignclient/user/service/UserService.java`

Aquí Feign no se usa directamente, sino a través del `Mapper`.

### 5.1. Obtener el correo por ID de usuario

```java
public MailDto getMailByUserId(UUID id)
```

Flujo:

1. `UserService` busca un usuario por su `id`
2. obtiene `user.getMailId()`
3. llama a `mapperUser.getMailById(user.getMailId())`
4. el `Mapper` usa Feign para llamar a `Mail`
5. devuelve el `MailDto`

### 5.2. Obtener el correo por nombre de usuario

```java
public MailDto getUserEmailByName(String name)
```

Flujo:

1. `UserService` busca el usuario por nombre
2. obtiene el `mailId`
3. llama al `Mapper`
4. el `Mapper` hace la llamada remota a `Mail`

### 5.3. Obtener usuario a partir del email

```java
public UserDto getUserByEmail(String email)
```

Este caso es muy interesante porque usa el otro endpoint:

```java
MailDto mail = mapperUser.getMailByName(email);
```

Flujo:

1. llega un email al servicio `User`
2. `User` no sabe directamente qué usuario tiene ese correo
3. primero consulta al microservicio `Mail` usando `GET /api/mail/name/{name}`
4. recibe un `MailDto`
5. con `mail.getId()` busca el usuario por `mailId`
6. finalmente devuelve el `UserDto`

Este es un buen ejemplo de colaboración entre microservicios:

- `Mail` conoce el correo
- `User` conoce el usuario
- Feign conecta ambos contextos

## 6. Uso dentro del controlador `UserController`

El controlador está en:

- `User/src/main/java/springboot/feignclient/user/controller/UserController.java`

Los endpoints que muestran esta integración son:

```java
@GetMapping("/name/{name}/email")
public ResponseEntity<MailDto> getUserEmailByName(@PathVariable String name)
```

Este endpoint:

1. recibe el nombre del usuario
2. llama a `userService.getUserEmailByName(name)`
3. el servicio usa el `Mapper`
4. el `Mapper` usa `MailFeignClient`
5. se obtiene el correo desde el microservicio `Mail`

Y también:

```java
@GetMapping("/{id}/email")
public ResponseEntity<MailDto> getUserEmailById(@PathVariable UUID id)
```

Este hace el mismo proceso, pero empezando por el `id` del usuario.

Otro endpoint interesante es:

```java
@GetMapping("/email/{email}")
public ResponseEntity<UserDto> getUserNameByEmail(@PathVariable String email)
```

Aquí el flujo es inverso:

1. llega un correo electrónico
2. `UserService` consulta `Mail` usando Feign
3. obtiene el `id` del correo
4. busca el usuario asociado a ese `mailId`
5. devuelve el `UserDto`

## 7. Resumen del flujo completo

### Caso 1: obtener el correo de un usuario

```text
UserController
 -> UserService
 -> Mapper
 -> MailFeignClient
 -> MailController
 -> MailService
 -> respuesta MailDto
```

### Caso 2: crear o actualizar un usuario validando el mail

```text
UserController
 -> UserService
 -> Mapper.convertToEntity(...) o Mapper.updateUser(...)
 -> MailFeignClient.getMailById(...)
 -> MailController.getMailById(...)
 -> validación del correo
 -> guardado del usuario
```

### Caso 3: buscar usuario a partir del email

```text
UserController
 -> UserService.getUserByEmail(...)
 -> Mapper.getMailByName(...)
 -> MailFeignClient.getMailByName(...)
 -> MailController.getMailByName(...)
 -> MailDto
 -> UserRepository.findByMailId(...)
 -> UserDto
```

## 8. Idea clave

Feign Client permite que el proyecto `User` consuma datos del proyecto `Mail` como si llamara a un método local, pero en realidad está haciendo una petición HTTP a otro servicio.

Eso hace que:

- `User` siga siendo responsable de usuarios
- `Mail` siga siendo responsable de correos
- ambos proyectos colaboren sin mezclar sus repositorios ni sus tablas
