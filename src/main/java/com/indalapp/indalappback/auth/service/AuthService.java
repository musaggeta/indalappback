package com.indalapp.indalappback.auth.service;

import com.indalapp.indalappback.auth.dto.LoginRequest;
import com.indalapp.indalappback.auth.dto.LoginResponse;
import com.indalapp.indalappback.users.entity.User;
import com.indalapp.indalappback.users.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Credenciales inválidas"
                ));

        if (!user.isActive()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Usuario inactivo"
            );
        }

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Credenciales inválidas"
            );
        }

        return new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                user.isActive(),
                "Inicio de sesión exitoso"
        );
    }
}