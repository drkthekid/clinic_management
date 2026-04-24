package chagas.com.br.clinic_management_system.controller.auth.patient;

import chagas.com.br.clinic_management_system.dto.request.LoginDTO;
import chagas.com.br.clinic_management_system.dto.request.RegisterDTO;
import chagas.com.br.clinic_management_system.dto.response.TokenResponseDTO;
import chagas.com.br.clinic_management_system.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String registerPatient(@Valid @RequestBody RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO loginPatient(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        return authService.login(loginDTO);
    }
}
