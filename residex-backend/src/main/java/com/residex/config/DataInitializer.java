package com.residex.config;

import com.residex.common.enums.Role;
import com.residex.user.entity.User;
import com.residex.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) {

        if (!userRepository.existsByEmail(
                "warden@residex.com"
        )) {

            User warden = new User();

            warden.setName("Main Warden");

            warden.setEmail(
                    "warden@residex.com"
            );

            warden.setPassword(
                    encoder.encode("warden123")
            );

            warden.setRole(
                    Role.WARDEN
            );

            userRepository.save(warden);
        }

        if (!userRepository.existsByEmail(
                "admin@residex.com"
        )) {

            User admin = new User();

            admin.setName("System Admin");

            admin.setEmail(
                    "admin@residex.com"
            );

            admin.setPassword(
                    encoder.encode("admin123")
            );

            admin.setRole(
                    Role.ADMIN
            );

            userRepository.save(admin);
        }
    }
}