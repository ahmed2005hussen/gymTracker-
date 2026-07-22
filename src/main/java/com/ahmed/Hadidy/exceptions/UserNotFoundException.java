package com.ahmed.Hadidy.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username){
        super("User not foud: " + username);
    }

}
