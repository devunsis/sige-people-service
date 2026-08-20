#!/usr/bin/env bash

export DB_HOST="132.18.41.109"
export DB_PORT="55432"
export DB_NAME="estudio_socioeconomico"
export DB_USER="admin"
export DB_PASS="root"

export JWT_SECRET="tu_clave_secreta_aqui"

# Usa ./mvnw para no depender de tener 'mvn' instalado globalmente
./mvnw spring-boot:run