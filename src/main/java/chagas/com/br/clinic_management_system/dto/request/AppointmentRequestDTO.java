package chagas.com.br.clinic_management_system.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record AppointmentRequestDTO(UUID patientId,
                                    LocalDateTime schedule,
                                    String status) {
}
