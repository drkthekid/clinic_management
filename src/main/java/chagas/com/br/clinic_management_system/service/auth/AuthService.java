package chagas.com.br.clinic_management_system.service.auth;

import chagas.com.br.clinic_management_system.database.entity.user.Role;
import chagas.com.br.clinic_management_system.database.entity.user.User;
import chagas.com.br.clinic_management_system.database.repository.user.UserRepository;
import chagas.com.br.clinic_management_system.dto.request.LoginDTO;
import chagas.com.br.clinic_management_system.dto.request.RegisterDTO;
import chagas.com.br.clinic_management_system.dto.response.TokenResponseDTO;
import chagas.com.br.clinic_management_system.exception.BadRequestException;
import chagas.com.br.clinic_management_system.exception.NotFoundException;
import chagas.com.br.clinic_management_system.infra.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private Role role;
    @Value("${jwt.expiration}")
    private long expirationTime;

    public String register(RegisterDTO registerDTO) {
        if (userRepository.findByEmail(registerDTO.email()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        userRepository.save(User.builder()
                .name(registerDTO.name())
                .email(registerDTO.email())
                .password(passwordEncoder.encode(registerDTO.password()))
                .roles(Set.of(Role.USER))
                .build());

        return "User Registered successfully";
    }

    public TokenResponseDTO login(LoginDTO loginDTO) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password())
            );

            String token = tokenProvider.generateToken(authentication);
            return TokenResponseDTO.builder()
                    .token(token)
                    .type("Bearer")
                    .expiration(expirationTime)
                    .build();
        } catch (BadRequestException e) {
            throw new BadRequestException("Invalid Credentials");
        } catch (Exception e) {
            throw new Exception("Erro interno inesperado: " + e.getMessage());
        }
    }

}
