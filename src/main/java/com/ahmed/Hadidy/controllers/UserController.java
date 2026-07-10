package com.ahmed.Hadidy.controllers;

import com.ahmed.Hadidy.dto.EditPasswordRequest;
import com.ahmed.Hadidy.entity.User;
import com.ahmed.Hadidy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {

  private final UserRepository userRepository ;
  private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try {

            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            User savedUser = userRepository.save(user);

            if(savedUser.getId() > 0){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("User with userName: " + user.getUsername() + " is created");
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User with userName: " + user.getUsername() + " is not created");
            }

        }catch(RuntimeException e ){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Exception occurred: " + e.getMessage());
        }
    }

    @PatchMapping("/editpassword")
    public ResponseEntity<String> editPassword(@RequestBody EditPasswordRequest request,
                                               Authentication authentication){

        try{
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow(
                    ()-> new RuntimeException("User with username = " + username +" ,not found")
            );


            if(passwordEncoder.matches(request.getOldPass() , user.getPassword()) ){
                String newEncodedPassword = passwordEncoder.encode(request.getNewPass());
                user.setPassword(newEncodedPassword);
                userRepository.save(user);

                return ResponseEntity.status(HttpStatus.OK).body(
                        "The password is edited for the user : " + user.getUsername()
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        "The password is not correct for the user : " +user.getUsername()
                );
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An Exception occurred: " + e.getMessage());
        }
    }
}
