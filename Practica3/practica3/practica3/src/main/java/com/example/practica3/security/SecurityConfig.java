package com.example.practica3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/api/users/**").permitAll() // Permitir acceso a todos los endpoints de usuarios
                //.anyRequest().authenticated()
            .and()
            .sessionManagement()
                .maximumSessions(1); // Permitir solo una sesión activa por usuario
        return http.build();
    }
}

