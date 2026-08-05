# SIGE-UNSIS: Manual de Arquitectura e Ingeniería Base

Este documento contiene los lineamientos estándar y obligatorios para el desarrollo de todos los microservicios del ecosistema insitucional SIGE-UNSIS.

---

## 1. Inicialización del Entorno Tecnológico (BP-01)

Cada módulo independiente debe ser generado bajos los siguientes parámetros unificados:

- **Lenguaje de Programación:** Java 21 LTS (Permite el uso de records nativos y rendimiento concurrente avanzado).
- **Gestor de Dependencias:** Maven[cite: 1].
- **Framework de Soporte:** Spring Boot 3.4.0.
- **Nomenclatura Institucional de Paquetes:** `mx.edu.unsis.sige.shared`[cite: 1].

### Dependencias de Base Obligatorias:

1. **Spring Web:** Para exposición de APIs REST (`ADAPTER IN`)[cite: 1].
2. **Spring Data JPA:** Para interactuar con los schemas de PostgreSQL 16 (`ADAPTER OUT`)[cite: 1].
3. **Validation (Jakarta):** Para la validación estricta de DTOs de entrada.
4. **Lombok:** Para la eliminación de código repetitivo en la capa de infraestructura.

## Compilación y Ejecución

Para compilar el proyecto localmente y verificar las dependencias, ejecuta:

```
./mvnw clean compile
```

---

## 2. Enfoque Arquitectónico: Arquitectura Hexagonal

El sistema adopta el patrón de **Arquitectura Hexagonal** (Ports & Adapters) para blindar la lógica de negocio institucional de los cambios tecnológicos externos[cite: 1]. Las dependencias fluyen estrictamente hacia adentro[cite: 1].

### Estructura de Directorios Estándar:

Las siguientes carpetas deben crearse dentro del paquete raíz `src/main/java/mx/edu/unsis/sige/shared/`[cite: 1]:

- `📂 domain/`: Lógica y modelos puros del negocio de la universidad[cite: 1]. **Prohibido incluir anotaciones de Spring o JPA/Hibernate aquí.**[cite: 1]
  - `📂 model/`: Objetos de negocio puros (POJOs)[cite: 1].
  - `📂 exception/`: Excepciones explícitas de negocio[cite: 1].
- `📂 application/`: Orquestación de procesos escolares[cite: 1].
  - `📂 ports/in/`: Interfaces que definen los casos de uso disponibles[cite: 1].
  - `📂 ports/out/`: Interfaces que definen los requerimientos externos (ej. repositorios)[cite: 1].
  - `📂 usecases/`: Implementación de la lógica de los puertos de entrada.
- `📂 infrastructure/`: Detalles técnicos y acoplamiento con frameworks[cite: 1].
  - `📂 adapters/in/web/`: Controladores REST (`@RestController`) que reciben peticiones de ElysiaJS[cite: 1].
  - `📂 adapters/in/dto/`: Modelos de transferencia de datos para peticiones/respuestas[cite: 1].
  - `📂 adapters/out/entity/`: Entidades JPA de Hibernate para PostgreSQL 16[cite: 1].
  - `📂 adapters/out/persistence/`: Implementación de los puertos de salida usando Spring Data JPA[cite: 1].x
  - `📂 config/`: Clases de configuración global del sistema[cite: 1].

**Estatus del Arquetipo Semilla:**

- [x] BP-01: Arquetipo de Proyecto (Estructura física de carpetas creada)
- [x] BP-06: Estándar de Respuesta (Estructura en dto/, manejo de HTTP Status y documentación actualizada)
- [ ] BP-03: Estándar de Manejo de Excepciones (Siguiente paso)
