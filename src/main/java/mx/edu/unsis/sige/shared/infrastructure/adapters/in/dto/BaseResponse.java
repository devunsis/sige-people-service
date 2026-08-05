package mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private int status;
    private LocalDateTime timestamp;
    private String correlationId; // Requerimiento de soporte técnico

    // --- MÉTODOS FACTORY PARA ÉXITOS ---
    public static <T> BaseResponse<T> success(T data, String message, int httpStatus) {
        return BaseResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .status(httpStatus)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> BaseResponse<T> success(T data, String message) {
        return success(data, message, 200);
    }

    public static <T> BaseResponse<T> success(T data) {
        return success(data, "Operación realizada con éxito", 200);
    }

    // --- MÉTODOS FACTORY PARA ERRORES ---
    public static <T> BaseResponse<T> error(List<String> errors, String message, int httpStatus, String correlationId) {
        return BaseResponse.<T>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .status(httpStatus)
                .timestamp(LocalDateTime.now())
                .correlationId(correlationId)
                .build();
    }

    public static <T> BaseResponse<T> error(String error, int httpStatus, String correlationId) {
        return error(List.of(error), error, httpStatus, correlationId);
    }

    public static <T> BaseResponse<T> error(String error, int httpStatus) {
        return error(List.of(error), error, httpStatus, null);
    }
}