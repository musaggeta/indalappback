package com.indalapp.indalappback.users.service;

import com.indalapp.indalappback.users.dto.CreateUserRequest;
import com.indalapp.indalappback.users.dto.UpdateUserRequest;
import com.indalapp.indalappback.users.dto.UserResponse;
import com.indalapp.indalappback.users.entity.User;
import com.indalapp.indalappback.users.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getRole().name(),
                        user.isActive()))
                .toList();
    }

    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un usuario con ese correo");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setActive(request.getActive());

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole().name(),
                savedUser.isActive());
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        userRepository.findByUsername(request.getUsername()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(id)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Ya existe un usuario con ese correo");
            }
        });

        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setActive(request.getActive());

        User updatedUser = userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getRole().name(),
                updatedUser.isActive());
    }

    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        user.setActive(false);
        userRepository.save(user);
    }
}
