package com.indalapp.indalappback.config;

import com.indalapp.indalappback.users.entity.User;
import com.indalapp.indalappback.users.entity.UserRole;
import com.indalapp.indalappback.users.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin@indal.com").isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User admin = new User(
                        "admin@indal.com",
                        encoder.encode("Admin123*"),
                        UserRole.ADMIN,
                        true
                );

                userRepository.save(admin);
            }
        };
    }
}