# 📘 Documentación: Carpeta `selected_db`

## 📂 Estructura

```
selected_db/
├── .env/
└── .vscode/
```

---

## 📌 Descripción general

La carpeta `selected_db` contiene la configuración necesaria para definir qué base de datos utilizar en la aplicación.  
Se apoya en variables de entorno y configuraciones del entorno de desarrollo para facilitar su uso y mantenimiento.

---

## 🔐 Carpeta `.env/`

### 🧠 ¿Qué es?

La carpeta `.env` contiene archivos con **variables de entorno**, que permiten configurar valores sensibles o variables sin necesidad de modificar el código fuente.

---

### ⚙️ ¿Cómo funciona?

1. Se definen variables en archivos dentro de `.env`
2. Estas variables son leídas por la aplicación en tiempo de ejecución
3. Se utilizan para configurar la conexión a la base de datos

---

### 🛠 Ejemplo de archivo `.env`

```
DB_HOST=localhost
DB_PORT=3306
DB_NAME=mi_base
DB_USER=root
DB_PASSWORD=1234
```

---

### 🔄 Flujo

1. La aplicación arranca  
2. Lee las variables del archivo `.env`  
3. Construye la configuración de conexión  
4. Se conecta a la base de datos  

---

### 💡 Ventajas

- Evita hardcodear credenciales
- Permite cambiar configuración sin tocar código
- Mejora la seguridad
- Facilita múltiples entornos (dev, test, prod)

---

## 🛠 Carpeta `.vscode/`

### 🧠 ¿Qué es?

La carpeta `.vscode` contiene configuraciones específicas del editor **Visual Studio Code** para el proyecto.

---

### ⚙️ Archivo de configuración

Normalmente incluye archivos como:

```
settings.json
launch.json
```

---

### 🔧 `settings.json`

Define configuraciones del editor, por ejemplo:

- Variables de entorno
- Formato de código
- Configuración del proyecto

Ejemplo:

```json
{
  "java.configuration.updateBuildConfiguration": "automatic"
}
```

---

### 🚀 `launch.json` (opcional)

Permite ejecutar y depurar la aplicación desde VS Code.

Ejemplo:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Run App",
      "request": "launch",
      "mainClass": "com.example.Main"
    }
  ]
}
```

---

## 🔄 Integración `.env` + `.vscode`

1. `.env` define las variables de entorno  
2. `.vscode` puede usarlas para ejecutar el proyecto  
3. VS Code lanza la aplicación con esa configuración  
4. La app usa esos valores para conectarse a la base de datos  

---

## ✅ Resumen

- `.env` → Configuración dinámica (variables de entorno)  
- `.vscode` → Configuración del entorno de desarrollo  
- Ambos trabajan juntos para facilitar el desarrollo y la conexión a la base de datos  

---

## ✍️ Autor

Proyecto Apuntes_Java
