package chagas.com.br.clinic_management_system.database.repository.user;

import chagas.com.br.clinic_management_system.database.entity.professional.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DentistRepository extends JpaRepository<Dentist, UUID> {
}
