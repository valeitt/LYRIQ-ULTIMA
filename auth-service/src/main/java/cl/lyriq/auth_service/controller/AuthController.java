package cl.lyriq.auth_service.controller;

import cl.lyriq.auth_service.dto.LoginDTO;
import cl.lyriq.auth_service.dto.RegisterDTO;
import cl.lyriq.auth_service.model.User;
import cl.lyriq.auth_service.repository.UserRepository;
import cl.lyriq.auth_service.security.JwtService;
import cl.lyriq.auth_service.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication",
        description = "Operaciones relacionadas con autenticación y usuarios"
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            AuthService authService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Operation(
            summary = "Registrar usuario",
            description = "Registra un nuevo usuario en el sistema"
    )
    @PostMapping("/register")
    public User register(
            @RequestBody RegisterDTO dto) {

        return authService.register(dto);
    }

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica un usuario y genera un token JWT"
    )
    @PostMapping("/login")
    public String login(
            @RequestBody LoginDTO dto) {

        User existingUser = userRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));

        if (!passwordEncoder.matches(
                dto.getPassword(),
                existingUser.getPassword())) {

            throw new RuntimeException(
                    "Password not found");
        }

        return jwtService.generateToken(
                existingUser.getUsername());
    }
}