package chagas.com.br.clinic_management_system.database.repository.user;

import chagas.com.br.clinic_management_system.database.entity.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findPatientById(UUID id);
}
