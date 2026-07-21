package com.ahmed.Hadidy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests((request) ->

                request.requestMatchers("/api/user/register").permitAll()
                        .requestMatchers("/api/user/editpassword" , "/api/hello" ,
                                "/api/profile/editprofile", "/api/profile/getprofile",
                                "/api/workoutplan/create" , "/api/workoutplan/list",
                                "/api/workoutplan/list/{id}" , "/api/workoutplan/delete/{id}"
                                , "/api/workoutplan/edit/{id}", "/api/workoutday/create" ,
                                "/api/workoutday/list/{workoutplanid}", "/api/workoutday/list/{workoutPlanId}/{workoutDayId}",
                                "/api/workoutday/delete/{workoutPlanId}/{workoutDayId}" , "/api/workoutday/edit/{workoutPlanId}/{workoutDayId}").authenticated()
                        .anyRequest().authenticated()

        );

        http.httpBasic(withDefaults());
        http.formLogin(withDefaults());
        http.csrf((cs)-> cs.disable());

        return http.build() ;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}