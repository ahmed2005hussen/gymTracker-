package com.ahmed.Hadidy.user.service;

import com.ahmed.Hadidy.user.dto.EditPasswordRequest;
import com.ahmed.Hadidy.user.dto.UserRequest;
import com.ahmed.Hadidy.user.entity.HadidyUser;

import java.util.Optional;

public interface UserService {
     Optional<HadidyUser> findByUsername(String username);
     HadidyUser registerUser(UserRequest userRequest);
     void changePassword(EditPasswordRequest editPasswordRequest  , String username );
}
