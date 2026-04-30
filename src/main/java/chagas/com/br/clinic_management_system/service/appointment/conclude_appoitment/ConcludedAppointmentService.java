package chagas.com.br.clinic_management_system.service.appointment.conclude_appoitment;

import chagas.com.br.clinic_management_system.database.entity.appointment.Appointment;
import chagas.com.br.clinic_management_system.database.entity.appointment.Status;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.appointment.AppointmentRepository;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import chagas.com.br.clinic_management_system.exception.BadRequestException;
import chagas.com.br.clinic_management_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConcludedAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public String concludedAppointment(Long id, UUID doctorId) {
        // pegar um appointment que existe e trocar o status para concluded porem é apenas o doutor responsavel que consegue fzr isso

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        User user = userRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));


        if (!appointment.getProfessional().getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You are not a professional that appointment");
        }

        if (appointment.getStatus() == Status.FINISHED || appointment.getStatus() == Status.CANCELED) {
            throw new BadRequestException("Appointment finished or canceled");
        }

        appointment.setStatus(Status.FINISHED);
        appointmentRepository.save(appointment);

        return "Appointment Concluded with Successfully";
    }

}
