package chagas.com.br.clinic_management_system.service.admin.list_users;

import chagas.com.br.clinic_management_system.database.entity.professional.Dentist;
import chagas.com.br.clinic_management_system.database.entity.professional.Doctor;
import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.user.DentistRepository;
import chagas.com.br.clinic_management_system.database.repository.user.DoctorRepository;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ListUsersService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DentistRepository dentistRepository;

    public Page<UserResponseDTO> findAllUsers(Role role, Integer page, Integer size) {
        Page<User> users;

        if (role != null) {
            users = userRepository.findAllByRolesContains(role, PageRequest.of(page, size));
        } else {
            users = userRepository.findAll(PageRequest.of(page, size));
        }

        return users.map(user -> UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .roles(
                        user.getRoles().stream()
                                .map(Enum::name)
                                .collect(Collectors.toSet()))
                .build()
        );
    }

    public UserResponseDTO findUserById(UUID id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

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

    public Page<UserResponseDTO> findAllDoctors(Integer page, Integer size) {
        Page<Doctor> doctors = doctorRepository.findAll(PageRequest.of(page, size));

        return doctors.map(doctor -> UserResponseDTO.builder()
                .id(doctor.getUser().getId())
                .name(doctor.getUser().getName())
                .email(doctor.getUser().getEmail())
                .roles(doctor.getUser().getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()))
                .createdAt(doctor.getUser().getCreatedAt())
                .build());
    }

    public Page<UserResponseDTO> findAllDentists(Integer page, Integer size) {
        Page<Dentist> dentists = dentistRepository.findAll(PageRequest.of(page, size));

        return dentists.map(dentist -> UserResponseDTO.builder()
                .id(dentist.getUser().getId())
                .name(dentist.getUser().getName())
                .email(dentist.getUser().getEmail())
                .roles(dentist.getUser().getRoles().stream()
                        .map(Enum::name)
                        .collect(Collectors.toSet()))
                .createdAt(dentist.getUser().getCreatedAt())
                .build());
    }
}
