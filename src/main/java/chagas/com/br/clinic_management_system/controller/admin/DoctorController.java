package chagas.com.br.clinic_management_system.controller.admin;

import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.service.admin.create_user.CreateUserStaffService;
import chagas.com.br.clinic_management_system.service.admin.list_users.ListUsersService;
import chagas.com.br.clinic_management_system.service.admin.update_users.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final ListUsersService listUsersService;
    private final CreateUserStaffService createUserStaffService;
    private final UpdateUserService updateUserService;

    @GetMapping
    public Page<UserResponseDTO> findAllUsersDoctors(
            @RequestParam int page,
            @RequestParam int size) {
        return listUsersService.findAllDoctors(page, size);
    }

    @PostMapping
    public UserResponseDTO createDoctor(@RequestBody UserRequestDTO userRequestDTO) {
        return createUserStaffService.createDoctor(userRequestDTO);
    }

    @PatchMapping("/{id}")
    public UserResponseDTO updateDoctor(@PathVariable UUID id,
                                        @RequestBody UserRequestDTO userRequestDTO) {
        return updateUserService.updateDoctor(id, userRequestDTO);
    }

}
