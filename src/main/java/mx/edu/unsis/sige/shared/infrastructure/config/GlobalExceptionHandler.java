package mx.edu.unsis.sige.shared.infrastructure.config;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mx.edu.unsis.sige.shared.domain.exception.BusinessException;
import mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 1. Captura excepciones de negocio (400 Bad Request)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(BusinessException ex) {
        BaseResponse<Void> response = BaseResponse.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 2. Captura errores de validación de campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        BaseResponse<Void> response = BaseResponse.error(
                fieldErrors,
                "Error de validación en los campos enviados",
                HttpStatus.BAD_REQUEST.value(),
                null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 3. Captura errores inesperados del sistema (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGlobalException(Exception ex) {
        // IMPRIMIR TRACE COMPLETO EN CONSOLA
        log.error("Excepción no controlada capturada en GlobalExceptionHandler:", ex);

        String correlationId = org.slf4j.MDC.get("correlationId");

        BaseResponse<Void> response = BaseResponse.error(
                "Ocurrió un error interno en el servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                correlationId);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}