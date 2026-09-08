package mx.edu.unsis.sige.people.infrastructure.adapters.in.dto.response;

import java.util.UUID;

/**
 * DTO de respuesta del catálogo de tipos de contacto.
 * Representación pública expuesta por la API; no se exponen entidades JPA.
 */
public record ContactTypeResponse(
        UUID id,
        String code,
        String name,
        String description,
        boolean active) {
}
