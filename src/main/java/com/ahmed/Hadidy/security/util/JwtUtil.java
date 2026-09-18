package com.ahmed.Hadidy.security.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

// To generate the JWT Token

@Component
@RequiredArgsConstructor
public class JwtUtil {

    public String generateJwt(Authentication authentication) {

        return "dummy for now :)";
    }


}
