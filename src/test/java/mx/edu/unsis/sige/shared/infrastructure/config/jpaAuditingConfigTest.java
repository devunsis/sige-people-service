package mx.edu.unsis.sige.shared.infrastructure.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JpaAuditingConfigTest {

    private AuditorAware<String> auditorAware;

    @BeforeEach
    void setUp() {
        JpaAuditingConfig config = new JpaAuditingConfig();
        auditorAware = config.auditorProvider();
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Debe retornar el usuario autenticado del SecurityContext")
    void shouldReturnAuthenticatedUserFromSecurityContext() {
        // Arrange
        String expectedUser = "usuario.unsis";
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                expectedUser, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Act
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // Assert
        assertTrue(currentAuditor.isPresent());
        assertEquals(expectedUser, currentAuditor.get());
    }

    @Test
    @DisplayName("Debe retornar SYSTEM cuando no hay usuario autenticado")
    void shouldReturnSystemWhenNoAuthenticationExists() {
        // Act
        Optional<String> currentAuditor = auditorAware.getCurrentAuditor();

        // Assert
        assertTrue(currentAuditor.isPresent());
        assertEquals("SYSTEM", currentAuditor.get());
    }
}