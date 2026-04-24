package chagas.com.br.clinic_management_system.dto.request;

public record RegisterDTO(
        String name,
        String password,
        String email
) {
}
