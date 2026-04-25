package chagas.com.br.clinic_management_system.service.admin.create_user;

import chagas.com.br.clinic_management_system.database.entity.professional.Dentist;
import chagas.com.br.clinic_management_system.database.entity.professional.Doctor;
import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.user.DentistRepository;
import chagas.com.br.clinic_management_system.database.repository.user.DoctorRepository;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
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

    @Transactional
    public UserResponseDTO createDoctor(UserRequestDTO userRequestDTO) {
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
                .build();

        doctorRepository.save(doctor);


        // return doctor
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

    @Transactional
    public UserResponseDTO createDentist(UserRequestDTO userRequestDTO) {
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
