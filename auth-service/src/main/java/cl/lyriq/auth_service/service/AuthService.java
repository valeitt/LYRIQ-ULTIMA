package cl.lyriq.auth_service.service;

import cl.lyriq.auth_service.dto.LoginDTO;
import cl.lyriq.auth_service.dto.RegisterDTO;
import cl.lyriq.auth_service.model.User;
import cl.lyriq.auth_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import cl.lyriq.auth_service.security.JwtService;

@Service
public class AuthService {

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

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    public String login(LoginDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user.getUsername());
    }
}