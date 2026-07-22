package com.ahmed.Hadidy.exceptions;

public class IncorrectPasswordException extends RuntimeException{

    public IncorrectPasswordException(){
        super("The Current password is incorrect");
    }
}
