# 📘 Documentación: selected_db

## 📂 Estructura

```
selected_db/
├── .env/
│   ├── mysql.env
│   └── postgresql.env
└── .vscode/
    └── launch.json
```

---

## 🔐 Carpeta `.env`

Contiene archivos de configuración para diferentes bases de datos.  
Cada archivo define las variables necesarias para conectarse a una base de datos específica.

---

## 🐬 `mysql.env`

### 📌 Propósito
Define la configuración para usar **MySQL** como base de datos.

### ⚙️ Funcionamiento

1. Se selecciona este archivo como fuente de variables de entorno  
2. La aplicación lee sus valores  
3. Se construye la conexión a MySQL  

### 🛠 Ejemplo

```
DB_TYPE=mysql
DB_HOST=localhost
DB_PORT=3306
DB_NAME=mi_db
DB_USER=root
DB_PASSWORD=1234
```

---

## 🐘 `postgresql.env`

### 📌 Propósito
Define la configuración para usar **PostgreSQL** como base de datos.

### ⚙️ Funcionamiento

1. Se selecciona este archivo como fuente de variables de entorno  
2. La aplicación lee sus valores  
3. Se construye la conexión a PostgreSQL  

### 🛠 Ejemplo

```
DB_TYPE=postgresql
DB_HOST=localhost
DB_PORT=5432
DB_NAME=mi_db
DB_USER=postgres
DB_PASSWORD=1234
```

---

## 🔄 Cambio de base de datos

El cambio entre MySQL y PostgreSQL se realiza:

1. Seleccionando el archivo `.env` correspondiente  
2. Ejecutando la aplicación con ese archivo  
3. La app usa automáticamente la configuración definida  

---

## 🛠 Carpeta `.vscode`

Contiene la configuración para ejecutar el proyecto desde Visual Studio Code.

---

## 🚀 `launch.json`

### 📌 Propósito
Permite ejecutar la aplicación desde VS Code con una configuración específica.

---

### ⚙️ Funcionamiento paso a paso

1. VS Code lee el archivo `launch.json`  
2. Se define qué clase principal ejecutar  
3. Se pueden definir variables de entorno  
4. Se lanza la aplicación con esa configuración  

---

### 🛠 Ejemplo

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Run with MySQL",
      "request": "launch",
      "mainClass": "com.example.Main",
      "envFile": "${workspaceFolder}/selected_db/.env/mysql.env"
    },
    {
      "type": "java",
      "name": "Run with PostgreSQL",
      "request": "launch",
      "mainClass": "com.example.Main",
      "envFile": "${workspaceFolder}/selected_db/.env/postgresql.env"
    }
  ]
}
```

---

## 🔄 Flujo completo

1. Se elige una configuración en `launch.json`  
2. VS Code carga el archivo `.env` correspondiente  
3. Se inyectan las variables de entorno  
4. La aplicación arranca  
5. Se conecta a la base de datos seleccionada  

---

## ✅ Resumen

- `mysql.env` → Configuración para MySQL  
- `postgresql.env` → Configuración para PostgreSQL  
- `launch.json` → Permite elegir cuál usar al ejecutar  

---
