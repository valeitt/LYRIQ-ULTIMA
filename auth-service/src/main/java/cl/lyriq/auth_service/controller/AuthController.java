package cl.lyriq.auth_service.controller;

import cl.lyriq.auth_service.dto.LoginDTO;
import cl.lyriq.auth_service.dto.RegisterDTO;
import cl.lyriq.auth_service.model.User;
import cl.lyriq.auth_service.repository.UserRepository;
import cl.lyriq.auth_service.security.JwtService;
import cl.lyriq.auth_service.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(AuthService authService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {

        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterDTO dto) {

        logger.info("Registering user: {}", dto.getUsername());

        return authService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        logger.info("Login attempt for user: {}",
                dto.getUsername());

        User existingUser = userRepository
                .findByUsername(dto.getUsername())
                .orElseThrow(() -> {
                    logger.error("User not found: {}",
                            dto.getUsername());
                    return new RuntimeException("User not found");
                });

        if (!passwordEncoder.matches(
                dto.getPassword(),
                existingUser.getPassword())) {

            logger.error("Invalid password for user: {}",
                    dto.getUsername());

            throw new RuntimeException("Password not found");
        }

        logger.info("Login successful for user: {}",
                dto.getUsername());

        return jwtService.generateToken(existingUser.getUsername());
    }
}