package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.request.EditPasswordRequest;
import com.ahmed.Hadidy.dto.request.UserRequest;
import com.ahmed.Hadidy.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequest user) {

        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");

    }

    @PatchMapping("/me/password")
    public ResponseEntity<String> editPassword(@Valid @RequestBody EditPasswordRequest request,
                                               Authentication authentication) {

        String username = authentication.getName();

        userService.changePassword(request, username);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Password changed successfully. "
        );
    }
}
