package com.example.mangaapi.config;

import com.example.mangaapi.entity.User;
import com.example.mangaapi.enums.Role;
import com.example.mangaapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    public ApplicationInitConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
           if( userRepository.findByUsername("admin").isEmpty()){
               var roles = new HashSet<String>();
               roles.add(Role.ADMIN.name());

               User user = User.builder()
                       .username("admin")
                       .roles(roles)
                       .password(passwordEncoder.encode("admin"))
                       .build();

               userRepository.save(user);
               log.warn("Admin user created with default password: admin, please change it");
           }

        };
    }
}
