# Atributos de fecha en `Mail`

En la entidad `Mail` se han definido dos atributos para registrar el ciclo de vida del registro:

- `createdTime`
- `updatedTime`

## `createdTime`

Este atributo guarda la fecha y hora en la que se crea el registro por primera vez.

En este caso se usa:

```java
@CreationTimestamp
private OffsetDateTime createdTime;
```

Eso significa que Hibernate asigna automaticamente la fecha de creacion cuando se inserta el `Mail` en la base de datos.

La idea es:

- cuando el mail se crea, se guarda la fecha inicial
- esa fecha representa el momento de alta del registro

## `updatedTime`

Este atributo guarda la fecha y hora de la ultima modificacion del registro.

En este caso se usa:

```java
@UpdateTimestamp
private OffsetDateTime updatedTime;
```

Eso significa que Hibernate actualiza automaticamente este campo cada vez que el registro cambia.

La idea es:

- si el registro se modifica, `updatedTime` cambia
- si no se modifica, mantiene la ultima fecha registrada

## Comportamiento esperado

Si se crea un `Mail`:

- `createdTime` se rellena con la fecha de creacion
- `updatedTime` puede quedar con la fecha de esa insercion inicial

Si despues se actualiza:

- `createdTime` no representa una nueva alta, sigue indicando cuando se creo
- `updatedTime` pasa a reflejar la fecha de la ultima modificacion

## Por que se usa `OffsetDateTime`

En esta entidad se ha usado `OffsetDateTime`, que permite guardar fecha y hora con informacion de zona u offset.

Eso resulta util cuando:

- quieres conservar mejor el contexto temporal
- trabajas con sistemas que pueden usar distintas zonas horarias

## Idea clave

En `Mail`, estos atributos sirven para auditar el registro:

- cuando se creo
- cuando se modifico por ultima vez

## Timestamp with timezone

```java
    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private OffsetDateTime createdTime;

    @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
    private OffsetDateTime updatedTime;

```
La idea es:
- `OffsetDateTime` incluye fecha, hora y offset/zona
- `TIMESTAMP_WITH_TIMEZONE` indica que en base de datos quieres conservar esa información temporal con zona
- Hibernate usa ese código para elegir el tipo JDBC correcto al leer y escribir
 
Mentalmente:
- `LocalDateTime` = fecha y hora “sin zona”
- `OffsetDateTime` = fecha y hora “con offset”
- `TIMESTAMP_WITH_TIMEZONE` encaja mejor con OffsetDateTime

Por eso en Mail tiene sentido usarlo.

 Qué pasa si no lo pones:
-  a veces Hibernate lo infiere bien solo
- pero con JdbcTypeCode(...) dejas explícito el tipo SQL/JDBC deseado
- eso ayuda cuando quieres controlar mejor el mapeo o evitar ambigüedades según la BD
- 
Por tanto:
- en Mail, `OffsetDateTime` + `TIMESTAMP_WITH_TIMEZONE` tiene mucho sentido
- en User, como usas `LocalDateTime`, normalmente bastaría con `TIMESTAMP` y muchas veces ni siquiera hace falta anotarlo

## ¿Qué es SqlTypes?

`SqlTypes` es una clase de Hibernate que define constantes para indicar el tipo JDBC/SQL que se quiere usar al mapear un atributo Java hacia base de datos.

### Chuletas de SqlTypes

#### Tipos de texto

- `VARCHAR`: texto variable de longitud normal.
- `CHAR`: texto de longitud fija.
- `LONGVARCHAR`: texto largo.
- `NVARCHAR`: texto variable con soporte Unicode o juego nacional de caracteres.
- `LONGNVARCHAR`: texto largo con soporte Unicode.

#### Tipos numericos

- `INTEGER`: numero entero estandar.
- `BIGINT`: entero grande.
- `SMALLINT`: entero pequeno.
- `TINYINT`: entero muy pequeno.
- `DECIMAL`: numero decimal exacto, util cuando importa la precision.
- `NUMERIC`: parecido a `DECIMAL`, decimal exacto con precision controlada.
- `FLOAT`: numero en coma flotante aproximado.
- `REAL`: numero decimal aproximado de menor precision.
- `DOUBLE`: numero decimal aproximado de doble precision.

#### Tipos booleanos

- `BOOLEAN`: valor verdadero o falso.
- `BIT`: valor binario, a veces usado como booleano segun la base de datos.

#### Tipos de fecha y hora

- `DATE`: solo fecha.
- `TIME`: solo hora.
- `TIME_WITH_TIMEZONE`: hora con zona u offset.
- `TIMESTAMP`: fecha y hora sin zona.
- `TIMESTAMP_WITH_TIMEZONE`: fecha y hora con zona u offset.

#### Tipos binarios

- `BINARY`: datos binarios de longitud fija.
- `VARBINARY`: datos binarios de longitud variable.
- `LONGVARBINARY`: datos binarios largos.
- `BLOB`: objeto binario grande, como imagenes, ficheros o contenido pesado.

#### Tipos de texto grande

- `CLOB`: texto muy grande.
- `NCLOB`: texto muy grande con soporte Unicode.

#### Tipos especiales o estructurados

- `UUID`: identificador unico universal.
- `JSON`: datos en formato JSON.
- `ARRAY`: arreglo o coleccion de valores.
- `XML`: datos en formato XML.
- `JAVA_OBJECT`: objeto Java tratado como objeto complejo o serializado.

#### Relacion rapida entre tipos Java y `SqlTypes`

- `String` -> `VARCHAR`
- `UUID` -> `UUID`
- `LocalDateTime` -> `TIMESTAMP`
- `OffsetDateTime` -> `TIMESTAMP_WITH_TIMEZONE`
- `boolean` o `Boolean` -> `BOOLEAN`
- texto largo -> `CLOB`
- JSON -> `JSON`

#### Aplicado a este proyecto

- En [`User`](https://github.com/AlvaroBlancoS/Apuntes_Java/tree/spring_boot/rest_template/User/src/main/java/springboot/resttemplate/user/entity), `LocalDateTime` encaja bien con `TIMESTAMP`.
- En `Mail`, `OffsetDateTime` encaja mejor con `TIMESTAMP_WITH_TIMEZONE`.

## Idea clave

`@JdbcTypeCode(...)` se usa cuando quieres dejar explicito como debe mapear Hibernate un atributo Java en la base de datos.

