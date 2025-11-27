# Guía de uso de Just y Flyway

## Just

Just es una herramienta para automatizar tareas mediante recetas definidas en un archivo `Justfile`. Permite ejecutar comandos complejos con solo escribir `just <tarea>`.

### Conceptos básicos

- **Justfile**: Archivo donde se definen las recetas (tareas).
- **Receta**: Una tarea definida que puede ejecutar uno o varios comandos.
- **Variable**: Se pueden definir variables para usar dentro de las recetas.

### Ejemplo de Justfile

```makefile
# Este es un ejemplo de Justfile
build:
    echo "Compilando el proyecto..."
test:
    echo "Ejecutando pruebas..."
```

### Comandos principales

- Ejecutar una receta: `just build`
- Listar todas las recetas: `just --list`
- Ejecutar receta con argumentos: `just test ARG=valor`

---

## Flyway

Flyway es una herramienta para gestionar versiones y migraciones de bases de datos mediante scripts SQL.

### Conceptos básicos

- **Migración**: Archivo SQL que contiene cambios a la base de datos.
- **Versionado**: Cada migración tiene un número de versión, por ejemplo `V1__crear_tabla.sql`.
- **Schema history**: Tabla interna de Flyway que registra qué migraciones se han aplicado.

### Estructura de archivos de migración

```
migrations/
├─ V1__crear_tabla.sql
├─ V2__insertar_datos.sql
```

### Comandos principales

- Aplicar migraciones: `flyway migrate`
- Revisar estado: `flyway info`
- Limpiar base de datos: `flyway clean`
- Revertir migración: `flyway undo` (solo si está habilitado)

### Ejemplo de uso

```bash
flyway -url=jdbc:postgresql://localhost:5432/mi_db -user=usuario -password=pass migrate
```

Esto aplicará todas las migraciones pendientes en la base de datos `mi_db`.
