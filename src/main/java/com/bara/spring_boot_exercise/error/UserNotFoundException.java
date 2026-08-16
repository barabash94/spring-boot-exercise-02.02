package com.bara.spring_boot_exercise.error;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super (message);
    }
}
