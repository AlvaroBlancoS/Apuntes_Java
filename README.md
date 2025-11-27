# Guia de instalaciones
-[Instalación desde Linux manual](#instalacion-desde-linux)

##Instalación desde linux manual
-[Instalación just](#instalación-just)
-[Instalación flyway](#instalación-flyway)

###Instalación just
Paso 1: Descargar el archivo comprimido
```bash
wget https://github.com/casey/just/releases/download/1.42.0/just-1.42.0-x86_64-unknown-linux-musl.tar.gz
```
Paso 2: Descomprimir el archivo
```bash
tar -xzf just-1.42.0-x86_64-unknown-linux-musl.tar.g
```
Paso 3: mover ese archivo a binario
```bash
sudo mv just /usr/local/bin/
```
Paso 4: Cambiar el permiso del administrador
```bash
sudo chmod +x /usr/local/bin/just
```
Paso 5: Comprobar la versión
```bash
just --version
```
