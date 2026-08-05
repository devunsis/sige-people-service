# ===================================================================
# FASE 1: COMPILACIÓN (Build Stage)
# ===================================================================
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copiar el archivo de configuración de dependencias
COPY pom.xml .

# Descargar las dependencias en caché para acelerar futuras compilaciones
RUN mvn dependency:go-offline -B

# Copiar el código fuente del proyecto
COPY src ./src

# Compilar y empaquetar el proyecto omitiendo los tests para el artefacto final
RUN mvn clean package -DskipTests

# ===================================================================
# FASE 2: EJECUCIÓN (Runtime Stage)
# ===================================================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear un usuario del sistema sin privilegios para mitigar riesgos de seguridad
RUN addgroup -S sige-group && adduser -S sige-user -G sige-group
USER sige-user

# Copiar únicamente el archivo JAR generado desde la fase de compilación
COPY --from=builder /app/target/sige-shared-boilerplate-*.jar app.jar

# Configurar variables de entorno por defecto expuestas en la tarea de Perfiles
ENV PORT=8080
ENV SPRING_PROFILE_ACTIVE=dev

# Informar el puerto en el que escuchará el contenedor
EXPOSE ${PORT}

# Comando de arranque optimizado pára entornos de contenedores
ENTRYPOINT [ "java", "-jar" , "app.jar"]
