# Guía de instalaciones

- [Instalación desde Linux manual](#instalación-desde-linux-manual)

## Instalación desde Linux manual

- [Instalación de Just](#instalación-de-just)
- [Instalación de Flyway](#instalación-de-flyway)

---

### Instalación de Just

**Paso 1: Descargar el archivo comprimido**
```bash
wget https://github.com/casey/just/releases/download/1.42.0/just-1.42.0-x86_64-unknown-linux-musl.tar.gz
```

**Paso 2: Descomprimir el archivo**
```bash
tar -xzf just-1.42.0-x86_64-unknown-linux-musl.tar.gz
```

**Paso 3: Mover el binario a /usr/local/bin**
```bash
sudo mv just /usr/local/bin/
```

**Paso 4: Dar permisos de ejecución**
```bash
sudo chmod +x /usr/local/bin/just
```

**Paso 5: Comprobar la versión**
```bash
just --version
```

---

### Instalación de Flyway

**Paso 1: Descargar Flyway**
```bash
wget https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/9.22.0/flyway-commandline-9.22.0-linux-x64.tar.gz
```

**Paso 2: Descomprimir Flyway**
```bash
tar -xzf flyway-commandline-9.22.0-linux-x64.tar.gz
```

**Paso 3: Mover a /usr/local/bin**
```bash
sudo mv flyway-9.22.0/flyway /usr/local/bin/
```

**Paso 4: Dar permisos de ejecución**
```bash
sudo chmod +x /usr/local/bin/flyway
```

**Paso 5: Comprobar la versión**
```bash
flyway -v
