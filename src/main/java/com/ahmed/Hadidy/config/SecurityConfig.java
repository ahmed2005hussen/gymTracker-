package com.ahmed.Hadidy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .authorizeHttpRequests(auth -> auth
//
//                        .requestMatchers(
//                                "/",
//                                "/index.html",
//                                "/Login.html",
//                                "/Register.html"
//                        ).permitAll()
//
//                        // files static
//                        .requestMatchers(
//                                "/images/**",
//                                "/api.js",
//                                "/style.css",
//                                "/favicon.ico"
//                        ).permitAll()
//
//                        // Swagger
//                        .requestMatchers(
//                                "/ahmed-ui.html",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/my-api-docs/**"
//                        ).permitAll()
//
//                        // APIs general
//                        .requestMatchers("/api/users/register").permitAll()

                                .anyRequest().authenticated()
                )

                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://localhost:5173",
                "http://127.0.0.1:5500",
                "http://localhost:5500"   // ضيفت النسخة من غير 127 كمان، المتصفحات بتفرّق بينهم
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}