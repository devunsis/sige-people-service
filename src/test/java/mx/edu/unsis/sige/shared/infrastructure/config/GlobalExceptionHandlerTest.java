package mx.edu.unsis.sige.shared.infrastructure.config;

import mx.edu.unsis.sige.shared.domain.exception.BusinessException;
import mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto.BaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Debe capturar BusinessException y retornar respuesta estructurada")
    void shouldHandleBusinessException() {
        // Arrange
        BusinessException exception = new BusinessException("Recurso no encontrado");

        // Act
        ResponseEntity<BaseResponse<Void>> response = exceptionHandler.handleBusinessException(exception);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals("Recurso no encontrado", response.getBody().getMessage());
    }
}