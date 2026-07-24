package com.ahmed.Hadidy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleDuplicateUsername
            (UsernameAlreadyExistsException exception){

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("message" , "Username already exists."));

    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Map<String,String>> handleIcorectPassword
            (IncorrectPasswordException exception){

        return ResponseEntity.badRequest().body(
                Map.of("message","Current password is incorrect.")
        );
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(
            UserNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "User was not found."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(Map.of("message", message));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException exception){

        return ResponseEntity.badRequest().body(
                Map.of("message" , exception.getMessage())
        );

    }

    @ExceptionHandler(DataNotExist.class)
    public ResponseEntity<Map<String , String>> handleDataNotExist(
            DataNotExist exception
    ){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("message" , exception.getMessage())
        );
    }

}
