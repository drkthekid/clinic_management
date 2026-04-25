package chagas.com.br.clinic_management_system.service.admin.update_users;

import chagas.com.br.clinic_management_system.database.entity.professional.Dentist;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.user.DentistRepository;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.exception.BadRequestException;
import chagas.com.br.clinic_management_system.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateUserService {

    private final UserRepository userRepository;
    private final DentistRepository dentistRepository;

    public UserResponseDTO updateDoctor(UUID id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        if (!userRequestDTO.email().isBlank()) {
            if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
                throw new BadRequestException("Email already exists");
            }

            user.setEmail(userRequestDTO.email());
        }

        if (!userRequestDTO.name().isBlank()) {
            user.setName(userRequestDTO.name());
        }

        userRepository.save(user);

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

    public UserResponseDTO updateDentist(UUID id, UserRequestDTO userRequestDTO) {
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dentist not found"));

        User user = dentist.getUser();

        if (userRequestDTO.name() != null && !userRequestDTO.name().isBlank()) {
            user.setName(userRequestDTO.name());
        }

        if (userRequestDTO.email() != null && !userRequestDTO.email().isBlank()) {
            if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
                throw new BadRequestException("Email already exists");
            }
            user.setEmail(userRequestDTO.email());
        }

        userRepository.save(user);

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
