package mx.edu.unsis.sige.shared.infrastructure.adapters.in.dto.request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record UserTestRequest(
        @NotBlank(message = "Username cannot be empty or null") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,

        @NotBlank(message = "Email cannot be empty or null") @Email(message = "Email must be a valid email address") String email,

        @NotBlank(message = "Password cannot be empty or null") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
}