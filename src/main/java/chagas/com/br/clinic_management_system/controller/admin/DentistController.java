package chagas.com.br.clinic_management_system.controller.admin;

import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.ProfessionalResponseDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.service.admin.create_users.CreateUserStaffService;
import chagas.com.br.clinic_management_system.service.admin.list_users.ListUsersService;
import chagas.com.br.clinic_management_system.service.admin.update_users.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final ListUsersService listUsersService;
    private final CreateUserStaffService createUserStaffService;
    private final UpdateUserService updateUserService;

    @GetMapping
    public Page<ProfessionalResponseDTO> findAllUsersDentists(
            @RequestParam int page,
            @RequestParam int size) {
        return listUsersService.findAllDentists(page, size);
    }

    @PostMapping
    public UserResponseDTO createDentist(@RequestBody UserRequestDTO userRequestDTO) {
        return createUserStaffService.createDentist(userRequestDTO);
    }

    @PatchMapping("/{id}")
    public UserResponseDTO updateDentist(@PathVariable UUID id,
                                         @RequestBody UserRequestDTO userRequestDTO) {
        return updateUserService.updateDentist(id, userRequestDTO);
    }
}
