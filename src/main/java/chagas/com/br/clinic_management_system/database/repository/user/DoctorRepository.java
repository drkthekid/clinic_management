package chagas.com.br.clinic_management_system.database.repository.user;

import chagas.com.br.clinic_management_system.database.entity.professional.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Doctor findByAvailabilityTrue();
}
