package chagas.com.br.clinic_management_system.service.admin.create_users;

import chagas.com.br.clinic_management_system.database.entity.admin.Admin;
import chagas.com.br.clinic_management_system.database.entity.professional.Dentist;
import chagas.com.br.clinic_management_system.database.entity.professional.Doctor;
import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.user.AdminRepository;
import chagas.com.br.clinic_management_system.database.repository.user.DentistRepository;
import chagas.com.br.clinic_management_system.database.repository.user.DoctorRepository;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.ProfessionalResponseDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateUserStaffService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;
    private final DentistRepository dentistRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public UserResponseDTO createAdmin(UserRequestDTO userRequestDTO) {

        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        User user = userRepository.save(User.builder()
                .name(userRequestDTO.name())
                .email(userRequestDTO.email())
                .password(passwordEncoder.encode(userRequestDTO.password()))
                .roles(Set.of(Role.ADMIN))
                .build()
        );

        Admin admin = Admin.builder()
                .user(user)
                .build();

        adminRepository.save(admin);

        return UserResponseDTO.builder()
                .id(admin.getUser().getId())
                .name(admin.getUser().getName())
                .email(admin.getUser().getEmail())
                .roles(admin.getUser().getRoles().stream().map(Enum::name)
                        .collect(Collectors.toSet()))
                .createdAt(admin.getUser().getCreatedAt())
                .build();
    }

    @Transactional
    public ProfessionalResponseDTO createDoctor(UserRequestDTO userRequestDTO) {

        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        User user = userRepository.save(
                User.builder()
                        .name(userRequestDTO.name())
                        .email(userRequestDTO.email())
                        .password(passwordEncoder.encode(userRequestDTO.password()))
                        .roles(Set.of(Role.STAFF)) // staff == doctor || dentist
                        .build()
        );

        // create doctor
        Doctor doctor = Doctor.builder()
                .user(user)
                .availability(true)
                .build();

        doctorRepository.save(doctor);


        // return doctor
        return ProfessionalResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .availability(doctor.getAvailability())
                .build();
    }

    @Transactional
    public UserResponseDTO createDentist(UserRequestDTO userRequestDTO) {

        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        User user = userRepository.save(
                User.builder()
                        .name(userRequestDTO.name())
                        .email(userRequestDTO.email())
                        .password(passwordEncoder.encode(userRequestDTO.password()))
                        .roles(Set.of(Role.STAFF)) // staff == doctor || dentist
                        .build()
        );

        Dentist dentist = Dentist.builder()
                .user(user)
                .build();

        dentistRepository.save(dentist);

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .build();
    }
}
