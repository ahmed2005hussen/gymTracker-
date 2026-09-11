package com.ahmed.Hadidy.profile.service;

import com.ahmed.Hadidy.profile.dto.ProfileResponse;
import com.ahmed.Hadidy.profile.dto.ProfileRequest;
public interface ProfileService {

    ProfileResponse editProfile(ProfileRequest request, String username);

    ProfileResponse getProfile(String username);

}
