package mx.edu.unsis.sige.shared.infrastructure.security.model;

import java.util.Set;
import java.util.UUID;

/**
 * Representa al usuario autenticado reconstruido directamente desde los
 * claims del JWT, sin consultar la base de datos. Se usa en cada peticion
 * posterior al login (via JwtAuthenticationFilter) para mantener el
 * modelo verdaderamente stateless.
 *
 * No confundir con UserPrincipal, que solo se usa una vez, durante el
 * login, cuando si es necesario consultar la BD para validar credenciales.
 */
public record AuthenticatedUser(
    UUID id,
    String username,
    String email,
    Set<String> roles,
    Set<String> permisos
) {}