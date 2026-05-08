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
