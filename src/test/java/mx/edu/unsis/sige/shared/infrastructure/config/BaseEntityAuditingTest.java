package mx.edu.unsis.sige.shared.infrastructure.config;

import mx.edu.unsis.sige.shared.domain.model.BaseEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseEntityAuditingTest {

    @BeforeEach
    void setUp() {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("admin_user", null,
                Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Debe permitir mapear y almacenar correctamente los campos de auditoría y ID UUID")
    void shouldMaintainAuditingAndIdPropertiesCorrectly() {
        // Arrange
        TestEntity entity = new TestEntity();
        UUID generatedId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        // Act
        entity.setId(generatedId);
        entity.setCreatedBy("admin_user");
        entity.setCreatedDate(now);
        entity.setLastModifiedBy("admin_user");
        entity.setLastModifiedDate(now);
        entity.setName("Prueba de Auditoría");

        // Assert
        assertNotNull(entity.getId(), "El ID UUID no debe ser nulo");
        assertEquals(generatedId, entity.getId());
        assertEquals("admin_user", entity.getCreatedBy());
        assertEquals(now, entity.getCreatedDate());
        assertEquals("admin_user", entity.getLastModifiedBy());
        assertEquals(now, entity.getLastModifiedDate());
        assertEquals("Prueba de Auditoría", entity.getName());
    }

    static class TestEntity extends BaseEntity {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}