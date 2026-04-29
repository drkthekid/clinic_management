package chagas.com.br.clinic_management_system.controller.user;


import chagas.com.br.clinic_management_system.dto.request.UserRequestDTO;
import chagas.com.br.clinic_management_system.dto.response.UserResponseDTO;
import chagas.com.br.clinic_management_system.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("#id == authentication.principal.id")
    @GetMapping("/{id}")
    public UserResponseDTO getInfo(@PathVariable UUID id) {
        return userService.findMe(id);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @PatchMapping("/{id}")
    public UserResponseDTO updateInfo(@PathVariable UUID id,
                                      @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateMe(id, userRequestDTO);
    }
}
