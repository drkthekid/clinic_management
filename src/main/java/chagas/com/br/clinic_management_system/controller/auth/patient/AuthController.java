package chagas.com.br.clinic_management_system.controller.auth.patient;

import chagas.com.br.clinic_management_system.dto.request.RegisterDTO;
import chagas.com.br.clinic_management_system.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String registerPatient(@RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }
}
