package chagas.com.br.clinic_management_system.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AppointmentResponseDTO(Long id,
                                     String type,
                                     UserSummary professional,
                                     UserSummary patient,
                                     LocalDateTime schedule,
                                     String status) {
}
