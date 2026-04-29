package chagas.com.br.clinic_management_system.service.appointment.create_appointment;

import chagas.com.br.clinic_management_system.database.entity.appointment.Appointment;
import chagas.com.br.clinic_management_system.database.entity.appointment.Status;
import chagas.com.br.clinic_management_system.database.entity.appointment.Type;
import chagas.com.br.clinic_management_system.database.entity.patient.Patient;
import chagas.com.br.clinic_management_system.database.entity.professional.Doctor;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.appointment.AppointmentRepository;
import chagas.com.br.clinic_management_system.database.repository.user.DoctorRepository;
import chagas.com.br.clinic_management_system.database.repository.user.PatientRepository;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import chagas.com.br.clinic_management_system.dto.request.AppointmentRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.AppointmentResponseDTO;
import chagas.com.br.clinic_management_system.dto.response.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CreateAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    // create exam with a doctor
    @Transactional
    public AppointmentResponseDTO createExamAppointmentWithDoctor(String email, AppointmentRequestDTO appointmentRequestDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow();


        Patient patient = user.getPatient();

        // patientRepository.save(patient);

        Doctor doctor = doctorRepository.findByAvailabilityTrue();

        Appointment appointment = appointmentRepository.save(Appointment.builder()
                .patient(patient)
                .professional(doctor)
                .type(Type.EXAM)
                .status(Status.PENDING)
                .schedule(appointmentRequestDTO.schedule())
                .build()
        );

        System.out.println(appointment);

        doctor.setAvailability(false);
        doctorRepository.save(doctor);

        return AppointmentResponseDTO.builder()
                .id(appointment.getId())
                .type(appointment.getType().name())
                .patient(new UserSummary(
                        appointment.getPatient().getUser().getId(),
                        appointment.getPatient().getUser().getName()
                ))
                .professional(new UserSummary(
                        appointment.getProfessional().getUser().getId(),
                        appointment.getProfessional().getUser().getName()
                ))
                .status(appointment.getStatus().name())
                .schedule(appointment.getSchedule())
                .build();
    }
}
