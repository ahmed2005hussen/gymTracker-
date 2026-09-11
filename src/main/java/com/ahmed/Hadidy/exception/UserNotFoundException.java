package com.ahmed.Hadidy.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username){
        super("User not foud: " + username);
    }

}
