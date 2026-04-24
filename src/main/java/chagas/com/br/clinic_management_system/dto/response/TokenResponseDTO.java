package chagas.com.br.clinic_management_system.dto.response;

import lombok.Builder;

@Builder
public record TokenResponseDTO(String token, String type, long expiration) {
}
