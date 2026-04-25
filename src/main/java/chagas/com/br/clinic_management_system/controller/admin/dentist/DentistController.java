package chagas.com.br.clinic_management_system.controller.admin.dentist;

import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.service.admin.create_user.CreateUserStaffService;
import chagas.com.br.clinic_management_system.service.admin.list_users.ListUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final ListUsersService listUsersService;
    private final CreateUserStaffService createUserStaffService;

    @GetMapping
    public Page<UserResponseDTO> findAllUsersDentists(
            @RequestParam int page,
            @RequestParam int size) {
        return listUsersService.findAllDentists(page, size);
    }

    @PostMapping
    public UserResponseDTO createDentist(@RequestBody UserRequestDTO userRequestDTO) {
        return createUserStaffService.createDentist(userRequestDTO);
    }
}
