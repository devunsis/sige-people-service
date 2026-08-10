package mx.edu.unsis.sige.shared.infrastructure.security;

import io.jsonwebtoken.Claims;
import mx.edu.unsis.sige.shared.infrastructure.security.model.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    private JwtProvider jwtProvider;
    private final String secret = "1234567890123456789012345678901234567890";

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider(secret, 60, "sige-auth-service");
    }

    @Test
    void shouldGenerateAndValidateTokenCorrectly() {
        UUID userId = UUID.randomUUID();
        UserPrincipal user = new UserPrincipal(
                userId,
                "admin",
                "admin@unsis.edu.mx",
                "hashedpassword",
                true,
                Set.of("ROLE_ADMIN"),
                Set.of()
        );

        // 1. Generar Token
        String token = jwtProvider.generarToken(user);
        assertNotNull(token);

        // 2. Extraer y verificar Claims
        Claims claims = jwtProvider.extraerClaims(token);
        assertNotNull(claims);
        assertEquals(userId.toString(), claims.getSubject());
        assertEquals("admin", claims.get("username"));
        assertEquals("sige-auth-service", claims.getIssuer());
    }

    @Test
    void ShouldRejectInvalidToken() {
        String tokenInvalido = "eyJhbGciOiJIUzI1NiJ9.invalid.signature";
        assertNull(jwtProvider.extraerClaims(tokenInvalido));
    }
}