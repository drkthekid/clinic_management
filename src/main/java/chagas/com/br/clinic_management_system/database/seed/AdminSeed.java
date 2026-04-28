package chagas.com.br.clinic_management_system.database.seed;

import chagas.com.br.clinic_management_system.database.entity.admin.Admin;
import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.user.AdminRepository;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminSeed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // evita duplicar admin
        if (userRepository.findByEmail("admin@gmail.com").isPresent()) {
            return;
        }

        User user = User.builder()
                .name("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("123"))
                .roles(Set.of(Role.ADMIN))
                .build();

        userRepository.save(user);

        Admin admin = Admin.builder()
                .user(user)
                .build();

        adminRepository.save(admin);
    }
}