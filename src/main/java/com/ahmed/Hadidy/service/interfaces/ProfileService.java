package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.dto.reponse.ProfileResponse;
import com.ahmed.Hadidy.dto.request.ProfileRequest;
public interface ProfileService {

    ProfileResponse editProfile(ProfileRequest request, String username);

    ProfileResponse getProfile(String username);

}
