package chagas.com.br.clinic_management_system.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank(message = "Name required") String name,
        @NotBlank(message = "Password required") String password,
        @NotBlank(message = "Email required") String email
) {
}
