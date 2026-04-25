package chagas.com.br.clinic_management_system.controller.admin;

import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.service.admin.create_users.CreateUserStaffService;
import chagas.com.br.clinic_management_system.service.admin.delete_users.DeleteUsersService;
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
    private final DeleteUsersService deleteUsersService;
    private final CreateUserStaffService createUserStaffService;

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

    @PostMapping
    public UserResponseDTO createAdmin(@RequestBody UserRequestDTO userRequestDTO) {
        return createUserStaffService.createAdmin(userRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        deleteUsersService.deleteUser(id);
    }

}
