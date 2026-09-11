package com.ahmed.Hadidy.user.service;

import com.ahmed.Hadidy.user.dto.EditPasswordRequest;
import com.ahmed.Hadidy.user.dto.UserRequest;
import com.ahmed.Hadidy.user.entity.User;

import java.util.Optional;

public interface UserService {
     Optional<User> findByUsername(String username);
     User registerUser(UserRequest userRequest);
     void changePassword(EditPasswordRequest editPasswordRequest  , String username );
}
