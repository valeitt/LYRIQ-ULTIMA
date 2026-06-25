package cl.lyriq.auth_service.service;

import cl.lyriq.auth_service.dto.LoginDTO;
import cl.lyriq.auth_service.dto.RegisterDTO;
import cl.lyriq.auth_service.model.User;
import cl.lyriq.auth_service.repository.UserRepository;
import cl.lyriq.auth_service.security.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User register(RegisterDTO dto) {

        logger.info("Registering user: {}", dto.getUsername());

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");

        User savedUser = userRepository.save(user);

        logger.info("User registered successfully: {}",
                savedUser.getUsername());

        return savedUser;
    }

    public String login(LoginDTO dto) {

        logger.info("Authenticating user: {}",
                dto.getUsername());

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> {
                    logger.error("User not found: {}",
                            dto.getUsername());
                    return new RuntimeException("User not found");
                });

        boolean matches = passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        );

        if (!matches) {

            logger.error("Invalid credentials for user: {}",
                    dto.getUsername());

            throw new RuntimeException("Invalid credentials");
        }

        logger.info("JWT token generated for user: {}",
                dto.getUsername());

        return jwtService.generateToken(user.getUsername());
    }
}