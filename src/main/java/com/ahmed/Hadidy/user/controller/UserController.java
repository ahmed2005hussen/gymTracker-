package com.ahmed.Hadidy.user.controller;

import com.ahmed.Hadidy.user.dto.EditPasswordRequest;
import com.ahmed.Hadidy.user.dto.UserRequest;
import com.ahmed.Hadidy.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
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
