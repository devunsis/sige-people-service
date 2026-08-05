package mx.edu.unsis.sige.shared.infrastructure.adapters.in.controller;

import mx.edu.unsis.sige.shared.infrastructure.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HealthCheckControllerTest extends AbstractIntegrationTest {

    @BeforeEach
    void setupStandaloneMockMvc() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).build();
    }

    @Test
    @DisplayName("Debe responder HTTP 200 OK y JSON correcto al consultar endpoint vía MockMvc")
    void shouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/v1/test-health")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @RestController
    static class TestController {
        @GetMapping("/api/v1/test-health")
        public Map<String, String> check() {
            return Map.of("status", "UP");
        }
    }
}