package chagas.com.br.clinic_management_system.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
public record ProfessionalResponseDTO(UUID id, String name, String email, Set<String> roles, LocalDateTime createdAt, Boolean availability) {
}
