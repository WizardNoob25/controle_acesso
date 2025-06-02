package com.devsuperior.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Essa é a configação em nível global, dei permitAll porque vou modifica em nivel de rota
@Configuration
public class SecurityConfig {

    @Bean // Vai virar um componente que pode ser injetado em outros lugares
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disabilita a proteção contra ataques de tipo csrf
        // Pois é um ataque usado quando vc tem uma aplicação que grava dados na sessão
        // A API Rest não grava dados na sessão
        http.csrf(csrf -> csrf.disable());

        // Configura a permissão para meus end-points/minhas requisições
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

}
