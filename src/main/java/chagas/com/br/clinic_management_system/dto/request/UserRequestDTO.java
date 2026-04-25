package chagas.com.br.clinic_management_system.dto.request;

import chagas.com.br.clinic_management_system.database.entity.user.Role;
import lombok.Builder;

@Builder
public record UserRequestDTO(
        String name,
        String email,
        String password,
        Role role
) {
}
