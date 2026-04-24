package chagas.com.br.clinic_management_system.database.repository.user;

import chagas.com.br.clinic_management_system.database.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
