package com.ahmed.Hadidy.profile.service.impl;

import com.ahmed.Hadidy.profile.dto.ProfileRequest;
import com.ahmed.Hadidy.profile.dto.ProfileResponse;
import com.ahmed.Hadidy.profile.entity.Profile;
import com.ahmed.Hadidy.user.entity.User;
import com.ahmed.Hadidy.exception.UserNotFoundException;
import com.ahmed.Hadidy.profile.repository.ProfileRepository;
import com.ahmed.Hadidy.profile.service.ProfileService;
import com.ahmed.Hadidy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImplementation implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;

    private double calculateBMI(double height, double weight) {
        return (weight / (height * height));

    }

    @Override
    @Transactional
    public ProfileResponse editProfile(ProfileRequest request, String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));


        Profile profile = user.getProfile();
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

        if (profile.getStartSubscribe() != null &&
                profile.getEndSubscribe() != null &&
                profile.getEndSubscribe().isBefore(profile.getStartSubscribe())) {
            throw new IllegalArgumentException(
                    "Subscription end date should be after start date"
            );
        }

        if (request.getWeight() != null) {
            profile.setWeight(request.getWeight());
        }
        if (request.getHeight() != null) {
            profile.setHeight(request.getHeight());

        }
        if (profile.getHeight() > 0 && profile.getWeight() > 0) {
            profile.setBmi(calculateBMI(profile.getHeight(), profile.getWeight()));
        }

        profileRepository.save(profile);

        return new ProfileResponse(profile);
    }

    @Override
    public ProfileResponse getProfile(String username) {

        User user = userService.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );

        return new ProfileResponse(user.getProfile());
    }

}
