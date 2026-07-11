package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.ProfileDto;
import com.ahmed.Hadidy.entity.Profile;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.ProfileRepository;
import com.ahmed.Hadidy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    final private UserRepository userRepository;
    final private ProfileRepository profileRepository;

    @PatchMapping("/editprofile")
    ResponseEntity<String> editProfile(@RequestBody ProfileDto request,
                                       Authentication authentication
    ) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not found")
            );

            Profile profile = user.getProfile();
            if (profile == null) {
                profile = new Profile();
                profile.setUser(user);
            }

            if (request.getProfilePicture() != null) {
                profile.setProfilePicture(request.getProfilePicture());
            }
            if (request.getGoal() != null) {
                profile.setGoal(request.getGoal());
            }
            if (request.getFullName() != null) {
                profile.setFullName(request.getFullName());
            }
            if (request.getEndSubscribe() != null) {
                profile.setEndSubscribe(request.getEndSubscribe());
            }
            if (request.getGmail() != null) {
                profile.setGmail(request.getGmail());
            }
            if (request.getGymPrice() != null) {
                profile.setGymPrice(request.getGymPrice());
            }
            if (request.getStartSubscribe() != null) {
                profile.setStartSubscribe(request.getStartSubscribe());
            }
            if (request.getWeight() != null) {
                profile.setWeight(request.getWeight());
            }
            if (request.getHeight() != null) {
                profile.setHeight(request.getHeight());

            }
            if (profile.getHeight() > 0 && profile.getWeight() > 0) {
                profile.setBmi(profile.getWeight() / (profile.getHeight() * profile.getHeight()));
            }

            profileRepository.save(profile);
            return ResponseEntity.status(HttpStatus.OK).body(
                    "Profile is Edited"
            );
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "An Exception occurred: " + e.getMessage()
            );
        }
    }

}
