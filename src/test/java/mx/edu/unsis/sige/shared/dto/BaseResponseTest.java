package mx.edu.unsis.sige.shared.dto;

import mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto.BaseResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseResponseTest {

    @Test
    @DisplayName("Debe crear una instancia de BaseResponse correctamente")
    void shouldCreateBaseResponse() {
        BaseResponse<String> response = BaseResponse.<String>builder()
                .success(true)
                .message("Operación exitosa")
                .data("Data de prueba")
                .build();

        assertNotNull(response);
        assertEquals("Operación exitosa", response.getMessage());
        assertEquals("Data de prueba", response.getData());
    }
}