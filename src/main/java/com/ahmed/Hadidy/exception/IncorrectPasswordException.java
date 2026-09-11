package com.ahmed.Hadidy.exception;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(){
        super("The Current password is incorrect");
    }
}
