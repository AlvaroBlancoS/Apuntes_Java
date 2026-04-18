# Apuntes de Spring Boot Feign Client

Este workspace está organizado como un proyecto Maven multi-módulo con dos aplicaciones:

- `Mail`
- `User`

La idea general es que `User` consume endpoints HTTP de `Mail` usando `Feign Client`.

## Estructura de los `pom.xml`

En este proyecto hay un `pom` padre y dos `pom` hijos:

- `pom.xml` en la raíz
- `Mail/pom.xml`
- `User/pom.xml`

## 1. `pom.xml` padre

El archivo raíz `pom.xml` actúa como padre del workspace.

Su función principal es:

- agrupar los módulos `Mail` y `User`
- compartir configuración común
- centralizar versiones

En el padre aparece:

```xml
<packaging>pom</packaging>
```

Eso significa que el proyecto raíz no genera un `jar` ejecutable, sino que sirve para coordinar módulos.

También aparecen:

```xml
<modules>
    <module>Mail</module>
    <module>User</module>
</modules>
```

Con esto Maven sabe que el workspace está formado por esos dos subproyectos.

Además, el padre define propiedades y dependencias comunes, por ejemplo:

- `java.version`
- `spring.boot.version`
- `lombok.version`

Y dentro de `dependencyManagement` fija versiones para que los hijos no tengan que repetirlas continuamente.

## 2. `pom.xml` hijos

Tanto `Mail/pom.xml` como `User/pom.xml` heredan del padre mediante:

```xml
<parent>
    <groupId>springboot.feignclient</groupId>
    <artifactId>workspace-parent</artifactId>
    <version>1.0.0</version>
    <relativePath>../pom.xml</relativePath>
</parent>
```

Esto permite que ambos módulos:

- reutilicen configuración común
- compartan versiones
- mantengan una estructura más limpia

Después, cada hijo añade sus propias dependencias según su responsabilidad.

## 3. Qué aporta cada hijo

### `Mail`

`Mail` es el microservicio que expone endpoints como:

- `GET /api/mail/{id}`
- `GET /api/mail/name/{name}`

Su trabajo es gestionar correos electrónicos y devolver datos de tipo `MailDto`.

### `User`

`User` es el microservicio que gestiona usuarios y, además, consume datos del proyecto `Mail`.

Aquí es donde aparece el uso real de `Feign Client`, porque `User` necesita llamar a:

- `GET /api/mail/{id}`
- `GET /api/mail/name/{name}`

para validar o recuperar información de correo.

## 4. Dependencia necesaria para Feign Client

Para que Feign funcione en un módulo, ese módulo debe incluir la dependencia:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

Y además suele importar el BOM de Spring Cloud en `dependencyManagement`:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## 5. Importante sobre los hijos y Feign

En esta práctica, los dos hijos tienen declarada la dependencia de OpenFeign en sus `pom.xml`.

Eso puede venir bien como apunte porque deja ambos módulos preparados para usar clientes Feign.

Pero siendo precisos:

- el módulo que consume otro servicio sí necesita `spring-cloud-starter-openfeign`
- el módulo que solo expone endpoints no tiene por qué necesitarla

En este proyecto, el consumidor claro es `User`, porque define:

- `MailFeignClient`

Por tanto, para que esta integración funcione de verdad, el hijo `User` sí debe incluir esa dependencia.

`Mail` no la necesita por exponer controladores, sino solo si también fuera a consumir otro microservicio mediante Feign.

## 6. Relación entre padre e hijos

La idea práctica es esta:

- el `pom` padre organiza el workspace
- cada hijo hereda del padre
- cada hijo declara sus dependencias concretas
- `User` añade lo necesario para consumir `Mail` con Feign

Dicho de otra forma:

- el padre comparte estructura
- los hijos implementan comportamiento

## 7. Flujo resumido de Feign en este workspace

1. `Mail` publica endpoints REST.
2. `User` declara una interfaz `MailFeignClient`.
3. `User` llama a `Mail` como si fuera un método Java normal.
4. OpenFeign convierte esa llamada en una petición HTTP real.
5. La respuesta se transforma en `MailDto`.

## 8. Idea clave

El `pom` padre no hace que Feign funcione por sí solo.

Lo que hace es organizar y compartir configuración.

Para que Feign funcione correctamente, el módulo hijo que vaya a consumir otro servicio debe declarar la dependencia de OpenFeign y configurar su cliente.
