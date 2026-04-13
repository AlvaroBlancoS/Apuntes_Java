# 📘 Configuración de Perfiles y Selección de Base de Datos

## 📌 Descripción General
Este módulo permite gestionar la configuración de la aplicación en función de diferentes **perfiles de ejecución** (por ejemplo: desarrollo, testing o producción), así como seleccionar dinámicamente la **base de datos activa**.

Su objetivo principal es desacoplar la configuración del código, facilitando la escalabilidad, mantenimiento y despliegue en distintos entornos.

---

## 🧱 Estructura del Módulo

```
configuration/
└── profiles/
    └── selected_db/
```

### 🔹 profiles/
Contiene la configuración asociada a distintos entornos o perfiles de ejecución.

### 🔹 selected_db/
Define qué base de datos se utilizará en función del perfil activo o de la configuración establecida.

---

## ⚙️ Funcionamiento

El sistema se basa en el uso de **perfiles** para cargar diferentes configuraciones según el entorno.

### 1. Selección de perfil
La aplicación identifica el perfil activo mediante:
- Variables de entorno
- Argumentos de ejecución
- Archivos de configuración

Ejemplo:
```
spring.profiles.active=dev
```

---

### 2. Carga de configuración
Dependiendo del perfil activo, se cargan diferentes configuraciones, como:

- URL de la base de datos
- Credenciales
- Driver JDBC
- Parámetros adicionales

---

### 3. Selección de base de datos
El módulo selected_db permite definir o cambiar dinámicamente la base de datos que se utilizará.

Esto puede implicar:
- Cambiar entre múltiples bases (ej: MySQL, PostgreSQL)
- Usar diferentes instancias según el entorno
- Redirigir conexiones sin modificar el código fuente

---

## 🔄 Flujo de ejecución

1. La aplicación inicia  
2. Se detecta el perfil activo  
3. Se cargan las configuraciones correspondientes  
4. Se selecciona la base de datos definida en selected_db  
5. Se establece la conexión  

---

## 💡 Ventajas

- Separación de configuración y lógica  
- Facilidad para cambiar de entorno (dev, test, prod)  
- Escalabilidad en proyectos grandes  
- Mejora en la seguridad (credenciales aisladas)  
- Adaptable a múltiples bases de datos  

---

## 🛠 Ejemplo de configuración

```properties
spring.profiles.active=dev
spring.datasource.url=jdbc:mysql://localhost:3306/dev_db
spring.datasource.username=root
spring.datasource.password=1234
```

---

## 🚀 Casos de uso

- Desarrollo local con base de datos independiente  
- Testing con base de datos simulada  
- Producción con credenciales seguras  
- Aplicaciones con múltiples clientes  

---

## 🧩 Posibles mejoras

- Implementar carga dinámica sin reinicio  
- Integrar múltiples fuentes de datos simultáneamente  
- Añadir logs para trazabilidad de configuración  
- Uso de variables de entorno para mayor seguridad  

---

## ✍️ Autor
Proyecto Apuntes_Java
