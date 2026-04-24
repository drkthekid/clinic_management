package chagas.com.br.clinic_management_system.controller.admin;

import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.service.admin.list_users.ListUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final ListUsersService listUsersService;

    @GetMapping("/users")
    public Page<UserResponseDTO> findAllUsers(
            @RequestParam(required = false) Role role,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return listUsersService.findAllUsers(role, page, size);
    }

}
