package chagas.com.br.clinic_management_system.database.repository.appointment;

import chagas.com.br.clinic_management_system.database.entity.appointment.Appointment;
import chagas.com.br.clinic_management_system.database.entity.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Page<Appointment> findAllAppointmentsByPatient(Patient patient, Pageable pageable);
}
