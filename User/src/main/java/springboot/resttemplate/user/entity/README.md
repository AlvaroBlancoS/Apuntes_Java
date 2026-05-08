# Atributos de fecha en `User`

En la entidad `User` se han definido dos atributos para registrar la creacion y la ultima modificacion del usuario:

- `createdTime`
- `updateTime`

## `createdTime`

Este atributo guarda la fecha y hora de creacion del usuario.

Se ha definido asi:

```java
@CreationTimestamp
@Column(name = "created_time", nullable = false, updatable = false)
private LocalDateTime createdTime;
```

Esto significa:

- Hibernate rellena automaticamente la fecha cuando se crea el usuario
- `updatable = false` evita que ese valor se cambie en una actualizacion

Por tanto:

- si el usuario se registra hoy, `createdTime` guardara esa fecha
- si el usuario se modifica manana, `createdTime` no cambia

## `updateTime`

Este atributo guarda la fecha y hora de la ultima actualizacion del usuario.

Se ha definido asi:

```java
@UpdateTimestamp
@Column(name = "update_time")
private LocalDateTime updateTime;
```

Esto significa:

- cuando el usuario se modifica, Hibernate actualiza automaticamente este campo
- cada cambio deja registrada una nueva fecha de actualizacion

Por tanto:

- al crear el usuario se registra su momento inicial
- al modificar el usuario, `updateTime` refleja la ultima modificacion

## Comportamiento esperado

Si se crea un usuario:

- `createdTime` se guarda con la fecha de alta
- `updateTime` puede quedar inicialmente con la fecha de insercion o actualizarse cuando haya cambios posteriores

Si despues se actualiza el usuario:

- `createdTime` permanece igual
- `updateTime` cambia a la fecha de la ultima edicion

## Por que esta configuracion es util

Esta solucion tiene varias ventajas:

- evita escribir codigo manual para manejar fechas
- reduce errores
- deja clara la diferencia entre fecha de creacion y fecha de actualizacion
- facilita auditoria y trazabilidad

## Idea clave

En `User`, estos atributos permiten distinguir entre:

- cuando nacio el registro
- cuando fue modificado por ultima vez
