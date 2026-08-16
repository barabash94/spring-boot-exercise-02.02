package com.bara.spring_boot_exercise.exceptionHandler;

import com.bara.spring_boot_exercise.error.ErrorResponse;
import com.bara.spring_boot_exercise.error.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException exception) {

        ErrorResponse error = new ErrorResponse(404,"User not found");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}
