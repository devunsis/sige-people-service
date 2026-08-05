package mx.edu.unsis.sige.shared.infrastructure.adapters.in.web;

import jakarta.validation.Valid;
import mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto.request.UserTestRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/test/validation")
public class TestValidationController {

    @PostMapping
    public ResponseEntity<Map<String, String>> testValidation(@Valid @RequestBody UserTestRequest request) {
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Data is valid! User " + request.username() + " processed successfully."));
    }
}