package chagas.com.br.clinic_management_system.service.appointment.list_appointments;

import chagas.com.br.clinic_management_system.database.entity.appointment.Appointment;
import chagas.com.br.clinic_management_system.database.repository.appointment.AppointmentRepository;
import chagas.com.br.clinic_management_system.dto.response.AppointmentResponseDTO;
import chagas.com.br.clinic_management_system.dto.response.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListAppointmentsService {

    private final AppointmentRepository appointmentRepository;

    // list all appointments for admin || list all appointments for user

    public Page<AppointmentResponseDTO> listAllAppointments(Integer page, Integer size) {
        Page<Appointment> appointments = appointmentRepository.findAll(PageRequest.of(page, size));

        return appointments.map(appointment -> AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .professional(new UserSummary(
                        appointment.getProfessional().getUser().getId(),
                        appointment.getProfessional().getUser().getName()
                ))
                .patient(new UserSummary(
                        appointment.getPatient().getUser().getId(),
                        appointment.getPatient().getUser().getName()
                ))
                .type(appointment.getType().name())
                .status(appointment.getStatus().name())
                .schedule(appointment.getSchedule())
                .build()
        );
    }


}
