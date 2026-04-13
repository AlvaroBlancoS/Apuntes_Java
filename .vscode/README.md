# 📘 Documentación: Configuración de `launch.json` y `tasks.json` en VS Code (Spring Boot Microservicios)

---

## 🧩 1. `launch.json` – Configuración de ejecución

El archivo `launch.json` define **cómo se ejecutan y depuran tus aplicaciones** en VS Code.

En tu caso, está diseñado para trabajar con microservicios:

* `Mail`
* `User`

Cada servicio tiene dos configuraciones:

* Normal
* Con **Hot Reload**

---

## ⚙️ Estructura básica

```json
{
    "type": "java",
    "name": "Mail Service",
    "request": "launch",
    "mainClass": "springboot.feignclient.mail.MailApplication",
    "projectName": "Mail",
    "cwd": "${workspaceFolder}/Mail",
    "console": "integratedTerminal",
    "preLaunchTask": "Maven: Build Mail",
    "envFile": "${workspaceFolder}/.env/postgres.env"
}
```

### 🔍 Explicación campo por campo

| Campo           | Descripción                                |
| --------------- | ------------------------------------------ |
| `type`          | Tipo de entorno (Java)                     |
| `name`          | Nombre visible en el selector de ejecución |
| `request`       | Tipo de ejecución (`launch` = iniciar app) |
| `mainClass`     | Clase principal de Spring Boot             |
| `projectName`   | Nombre del proyecto en VS Code             |
| `cwd`           | Directorio de trabajo del microservicio    |
| `console`       | Dónde se muestra la salida                 |
| `preLaunchTask` | Tarea que se ejecuta antes (build)         |
| `envFile`       | Archivo de variables de entorno            |

---

## 🔥 2. ¿Qué es Hot Reload?

Hot Reload permite que la aplicación se **reinicie automáticamente al detectar cambios en el código**, sin reiniciar manualmente.

Esto funciona gracias a:

👉 `spring-boot-devtools`

---

## 🤔 Diferencia clave: automático vs `vmArgs`

### ✅ Caso 1: Sin `vmArgs`

```json
{
    "name": "Mail Service"
}
```

✔ Si tienes `spring-boot-devtools` en el proyecto:

* Hot Reload **funciona automáticamente**
* Spring detecta cambios y reinicia

👉 No necesitas hacer nada más

---

### ⚡ Caso 2: Con `vmArgs`

```json
"vmArgs": "-Dspring.devtools.restart.enabled=true"
```

✔ Esto **fuerza explícitamente** el comportamiento

### 📌 ¿Qué significa?

* Activa el reinicio incluso si está deshabilitado
* Es útil si:

  * Hay problemas de configuración
  * Estás en entornos raros (Docker, IDEs, etc.)

---

## 🧠 Resumen claro

| Situación         | Resultado                |
| ----------------- | ------------------------ |
| DevTools presente | Hot Reload funciona solo |
| `vmArgs` añadido  | Se fuerza explícitamente |
| Sin DevTools      | ❌ No hay Hot Reload      |

👉 En la práctica:

> `vmArgs` es redundante en la mayoría de casos, pero útil como "seguro".

---

## 🛠️ 3. `tasks.json` – Automatización de tareas

El archivo `tasks.json` define tareas que VS Code puede ejecutar, como:

* Compilar
* Ejecutar scripts
* Construir proyectos

---

## ⚙️ Ejemplo

```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "Maven: Build Mail",
            "type": "shell",
            "command": "mvn clean install",
            "options": {
                "cwd": "${workspaceFolder}/Mail"
            },
            "group": "build",
            "problemMatcher": []
        }
    ]
}
```

---

## 🔍 Explicación campo por campo

| Campo            | Descripción                                 |
| ---------------- | ------------------------------------------- |
| `label`          | Nombre de la tarea (usado en `launch.json`) |
| `type`           | Tipo de ejecución (`shell`)                 |
| `command`        | Comando que se ejecuta                      |
| `options.cwd`    | Carpeta donde se ejecuta                    |
| `group`          | Grupo lógico (build, test, etc.)            |
| `problemMatcher` | Detecta errores automáticamente (opcional)  |

---

## 🔗 Relación entre `launch.json` y `tasks.json`

```json
"preLaunchTask": "Maven: Build Mail"
```

👉 Esto significa:

1. VS Code ejecuta la tarea definida en `tasks.json`
2. Luego arranca la aplicación

---

## ⚠️ Problema común

Error:

```
No se encuentra la tarea: Maven: Build Mail
```

👉 Ocurre cuando:

* No existe en `tasks.json`
* El nombre (`label`) no coincide exactamente

---

## 💡 Buenas prácticas

### ✔ Usa `.env`

* Evita duplicación
* Centraliza configuración

### ✔ Usa tareas solo si las necesitas

* En desarrollo, puedes omitir `preLaunchTask`
* Spring Boot compila automáticamente

### ✔ Usa Maven Wrapper (`mvnw`)

```bash
./mvnw clean install
```

✔ Más portable
✔ No depende de instalación global

---

## 🚀 Flujo completo

1. Seleccionas configuración en VS Code
2. (Opcional) Se ejecuta `preLaunchTask`
3. Se cargan variables desde `.env`
4. Se inicia Spring Boot
5. DevTools activa Hot Reload automáticamente

---

## 🧠 Conclusión

* `launch.json` → controla **cómo se ejecuta la app**
* `tasks.json` → controla **qué se ejecuta antes**
* Hot Reload:

  * Funciona solo con DevTools
  * `vmArgs` es solo un refuerzo

---

## 📌 Recomendación final

Para desarrollo diario:

✔ Usa `envFile`
✔ Usa DevTools
✔ Evita tareas innecesarias
✔ Mantén configuraciones simples y explícitas

---

Esto te deja con un entorno limpio, mantenible y profesional para microservicios 🚀