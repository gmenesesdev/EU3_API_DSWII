package evu3_api.evu3_api_grupo11.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                // Permitir acceso público solo a operaciones de lectura específicas
                .requestMatchers("/api/estudiantes/{id}/registros").authenticated()
                .requestMatchers("/api/estudiantes/{id}/registros/**").authenticated()
                .requestMatchers("/api/profesores/practicas").authenticated()
                .requestMatchers("/api/profesores/practicas/**").authenticated())
                .httpBasic(Customizer.withDefaults()) // Usar autenticación básica
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF para facilitar pruebas
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // Definir usuarios en memoria
        User.UserBuilder users = User.builder();

        UserDetails profesor = users
                .username("profesor")
                .password(passwordEncoder.encode("password123"))
                .roles("PROFESOR")
                .build();

        UserDetails estudiante = users
                .username("estudiante")
                .password(passwordEncoder.encode("password123"))
                .roles("ESTUDIANTE")
                .build();

        return new InMemoryUserDetailsManager(profesor, estudiante);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}