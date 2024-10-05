package com.congrats.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Value("${spring.security.user.name}")
        private String username;

        @Value("${spring.security.user.password}")
        private String password;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.NEVER))
                                .authorizeHttpRequests(authorize -> authorize
                                                // Permitir accesos públicos a rutas específicas
                                                .requestMatchers("/api/authenticate").permitAll()
                                                .requestMatchers("/api/projects").permitAll()
                                                .requestMatchers("/api/projects/searchByName/**").permitAll()
                                                .requestMatchers("/api/projects/searchById/**").permitAll()
                                                .requestMatchers("/api/projects/getAllPaginated/**").permitAll()
                                                .requestMatchers("/api/media/getAll").permitAll()
                                                .requestMatchers("/api/media/getById/**").permitAll()
                                                .requestMatchers("/api/mail/sendEmail").permitAll()
                                                .requestMatchers("/api/mail/sendEmailToClient/**").permitAll()
                                                // Rutas protegidas con Basic Authentication
                                                .requestMatchers("/swagger-ui/**").hasRole("ADMIN")
                                                .requestMatchers("/v3/api-docs/**").hasRole("ADMIN")
                                                .requestMatchers("/api/projects/create").hasRole("ADMIN")
                                                .requestMatchers("/api/projects/update/**").hasRole("ADMIN")
                                                .requestMatchers("/api/projects/delete/**").hasRole("ADMIN")
                                                .requestMatchers("/api/contacts/update/**").hasRole("ADMIN")
                                                .requestMatchers("/api/contacts/delete/**").hasRole("ADMIN")
                                                .requestMatchers("/api/contacts/getById/**").hasRole("ADMIN")
                                                .requestMatchers("/api/contacts/getAll/**").hasRole("ADMIN")
                                                .requestMatchers("/api/media/create").hasRole("ADMIN")
                                                .requestMatchers("/api/media/update/**").hasRole("ADMIN")
                                                .requestMatchers("/api/media/delete/**").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(formLogin -> formLogin.permitAll());
                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                PasswordEncoder encoder = passwordEncoder();
                String encodedPassword = encoder.encode(password);

                UserDetails admin = User.builder()
                                .username(username)
                                .password(encodedPassword)
                                .roles("ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(admin);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
}