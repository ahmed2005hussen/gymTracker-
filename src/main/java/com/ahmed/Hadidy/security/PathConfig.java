package com.ahmed.Hadidy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PathConfig {

    //"/", "/*.html","/images/**","/api.js","/style.css","/favicon.ico"
    //,"/apple-touch-icon*.png", "/*.png","/*.ico"

    @Bean(name = "publicPaths")
    public List<String> publicPath() {
        return List.of(
                // swagger's APIs
                "/swagger-ui/**",
                "/api/swagger-ui.html",
                "/api/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"

                // register and login
        );
    }

    @Bean(name = "securePaths")
    public List<String> privatePath() {
        return List.of(
                // My API
                "/api/diet-plans/**",
                "/api/workout-plans/{workoutPlanId}/days/{workoutDayId}/exercises/**",
                "/api/diet-plans/{dietPlanId}/meals/**",
                "/api/users/me/profile",
                "/api/supplements/**",
                "/api/users",
                "/api/workout-plans/{workoutPlanId}/days/**",
                "/api/workout-plan/**"
        );
    }

}
