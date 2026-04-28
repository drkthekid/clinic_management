package chagas.com.br.clinic_management_system.database.repository.appointment;

import chagas.com.br.clinic_management_system.database.entity.appointment.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
