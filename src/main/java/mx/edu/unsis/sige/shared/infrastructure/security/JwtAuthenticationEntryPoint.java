package mx.edu.unsis.sige.shared.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.unsis.sige.shared.infrastructure.exception.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component // <-- Faltaba esta anotación para que Spring lo detecte como Bean
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException { // <-- Ya tiene importada IOException

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                "Se requiere autenticación completa para acceder a este recurso",
                HttpStatus.UNAUTHORIZED.value(),
                request.getRequestURI(),
                LocalDateTime.now());

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}