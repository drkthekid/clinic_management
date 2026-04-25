package chagas.com.br.clinic_management_system.controller.admin.user;

import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.service.admin.create_user.CreateUserStaffService;
import chagas.com.br.clinic_management_system.service.admin.list_users.ListUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class AdminController {

    private final ListUsersService listUsersService;

    @GetMapping
    public Page<UserResponseDTO> findAllUsers(
            @RequestParam(required = false) Role role,
            @RequestParam int page,
            @RequestParam int size) {
        return listUsersService.findAllUsers(role, page, size);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable UUID id) {
        return listUsersService.findUserById(id);
    }

}
