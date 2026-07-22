package com.ahmed.Hadidy.service.interfaces;

import com.ahmed.Hadidy.dto.request.EditPasswordRequest;
import com.ahmed.Hadidy.dto.request.UserRequest;
import com.ahmed.Hadidy.entity.User;

import java.util.Optional;

public interface UserService {
     Optional<User> findByUsername(String username);
     User registerUser(UserRequest userRequest);

     void changePassword(EditPasswordRequest editPasswordRequest  , String username );
}
