# Excepciones: las tres piezas

Esta carpeta reúne una idea muy útil: **cuando algo va mal, la API responde siempre de una forma comprensible y predecible**.

Piensa en el recorrido así:

```text
Petición HTTP
     |
     +--> ¿Los datos del DTO cumplen sus reglas? -- no --> MethodArgumentNotValidException
     |                                                   |
     |                                                   v
     |                                         GlobalExceptionHandler --> 400
     |
     +--> La lógica busca o comprueba algo -- falla --> CustomException
                                                        |
                                                        v
                                             GlobalExceptionHandler --> estado del enum
```

> Objetivo: en los servicios y mappers solo indicamos **qué ha fallado**. El [`GlobalExceptionHandler`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/util/exception/GlobalExceptionHandler.java#L17) decide **cómo responder al cliente**.

---

## 1. [`GlobalExceptionHandler`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/util/exception/GlobalExceptionHandler.java#L17): la centralita de errores

La anotación `@RestControllerAdvice` hace que esta clase escuche las excepciones que se produzcan en los controladores REST de toda la aplicación.

Cada método marcado con [`@ExceptionHandler(...)`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/util/exception/GlobalExceptionHandler.java#L21) captura un tipo concreto de excepción y construye una respuesta JSON. Así evitamos repetir `try/catch` en cada endpoint.

La respuesta incluye datos comunes:

| Campo | Para qué sirve |
| --- | --- |
| `timestamp` | Cuándo ocurrió el error. |
| `status` | Código HTTP numérico, por ejemplo `400` o `404`. |
| `error` | Nombre HTTP del error, por ejemplo `Bad Request`. |
| `code` | Código estable que puede usar el front-end. |
| `message` | Mensaje que puede leer una persona. |
| `path` | Endpoint que recibió la petición. |

### A. Errores de validación: `MethodArgumentNotValidException`

Este manejador se activa cuando Spring intenta validar un DTO y alguna regla no se cumple.

Para que ocurra hacen falta **dos piezas**:

1. El DTO tiene reglas de Bean Validation, por ejemplo `@NotNull`, `@NotBlank` o `@Pattern`.
2. El endpoint recibe ese DTO con `@Valid`.

Ejemplo:

```java
public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto) {
    // Solo llegamos aquí si UserDto es válido.
}
```

En este proyecto, [`UserDto`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/dto/UserDto.java#L17), [`ChangePasswordDto`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/dto/ChangePasswordDto.java#L17), [`LoginUserDto`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/dto/LoginUserDto.java#L18) y otros DTO ya contienen anotaciones de validación. Por ejemplo, `ChangePasswordDto` no permite que `userName`, `currentPassword` o `newPassword` sean `null` ni texto vacío. `UserDto` también exige una contraseña con el patrón indicado.

**Pausa importante:** tener anotaciones en el DTO no basta por sí solo. Hay que poner `@Valid` en el parámetro del controlador que recibe el `@RequestBody`. Sin `@Valid`, Spring no ejecuta estas comprobaciones automáticas al entrar en el endpoint.

Si falla una regla, Spring crea [`MethodArgumentNotValidException`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/util/exception/GlobalExceptionHandler.java#L22). El método `handleValidationErrors`:

1. Lee los errores con `ex.getBindingResult()`.
2. Se queda con el **primer** error (`findFirst()`).
3. Devuelve `400 Bad Request` con el campo, el valor rechazado y el mensaje de la anotación.

Ejemplo de respuesta para una contraseña inválida:

```json
{
  "timestamp": "2026-09-19T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "code": "VALIDATION_ERROR",
  "message": "La contraseña debe tener al menos 8 caracteres...",
  "field": "password",
  "rejectedValue": "abc",
  "path": "/users"
}
```

> Ahora se informa de un único error cada vez. Es una decisión válida para simplificar la respuesta. Si un día se quieren mostrar todos, habría que recorrer `getFieldErrors()` en vez de usar `findFirst()`.

---

## 2. `CustomException`: la forma de decir «este caso de negocio ha fallado»

No todos los errores dependen de la forma de los datos. A veces los datos son válidos, pero la operación no puede continuar:

- Se busca un correo que no existe.
- Se intenta crear un usuario que ya existe.
- La contraseña actual no coincide.

Ahí usamos [`CustomException`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/util/exception/CustomException.java#L7):

```java
throw new CustomException(MessageException.MAIL_NOT_FOUND);
```

Esta excepción extiende `RuntimeException`, por lo que no obliga a añadir `throws` ni a capturarla en cada capa. Guarda una constante `MessageException`, es decir, toda la información del error elegido.

Ejemplo real en `MailMapper`:

```java
return emailRepository.findById(id)
        .orElseThrow(() -> new CustomException(MessageException.MAIL_NOT_FOUND));
```

Lectura humana: «busca el correo; si no existe, lanza el error `MAIL_NOT_FOUND`».

Después, [`handleCustomException`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/util/exception/GlobalExceptionHandler.java#L50) recibe esa excepción, extrae su `MessageException` y responde con el estado, código y mensaje definidos allí. La capa que lanza el error no necesita saber nada de JSON ni de `ResponseEntity`.

---

## 3. [`MessageException`](https://github.com/AlvaroBlancoS/Apuntes_Java/blob/spring_boot/token/CommonBookstore/src/main/java/springboot/util/exception/MessageException.java#L10): el catálogo de errores permitidos

`MessageException` es un `enum`: una lista cerrada de errores conocidos. Cada elemento contiene cuatro datos:

```java
MAIL_NOT_FOUND(
    "mail-1003",                  // código técnico
    "Email was not found",         // mensaje en inglés
    "Correo electrónico no fue encontrado", // mensaje en español
    HttpStatus.NOT_FOUND            // estado HTTP: 404
)
```

Esto aporta orden:

- No se inventan mensajes distintos para el mismo caso.
- El front-end puede reaccionar al `code`, que es más estable que el texto.
- El estado HTTP está centralizado y es coherente.
- Se puede mostrar el mensaje en español o en inglés con `getMessage(locale)`.

Al añadir un nuevo caso de negocio, primero se incorpora una constante al `enum` y después se lanza donde corresponda:

```java
// 1. En MessageException
BOOK_NOT_FOUND("book-1001", "Book was not found", "Libro no encontrado", HttpStatus.NOT_FOUND)

// 2. En el servicio o mapper
throw new CustomException(MessageException.BOOK_NOT_FOUND);
```

No hace falta añadir otro `@ExceptionHandler`: el existente para `CustomException` ya sabe transformar cualquier constante del enum en una respuesta HTTP.

---

## 4. Resumen para recordar

```text
¿El JSON recibido está incompleto o tiene un formato inválido?
    -> DTO con anotaciones + @Valid en el endpoint
    -> MethodArgumentNotValidException
    -> respuesta 400 con campo y mensaje.

¿La petición tiene buena forma, pero falla una regla del negocio?
    -> throw new CustomException(MessageException.ALGO)
    -> respuesta con el estado, código y mensaje del enum.
```
