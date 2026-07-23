package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.reponse.ProfileResponse;
import com.ahmed.Hadidy.dto.request.ProfileRequest;
import com.ahmed.Hadidy.service.interfaces.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/me/profile")
@RequiredArgsConstructor
public class ProfileController {

    final private ProfileService profileService;

    @PatchMapping
    public ResponseEntity<ProfileResponse> editProfile(@Valid @RequestBody ProfileRequest request,
                                                       Authentication authentication
    ) {

        String username = authentication.getName();

        ProfileResponse profile = profileService.editProfile(request, username);
        return ResponseEntity.status(HttpStatus.OK).body(
                profile
        );

    }

    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK).body(
                profileService.getProfile(authentication.getName())
        );


    }
}
