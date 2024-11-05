package com.example.session18.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            switch (fieldName) {
                case "name":
                    response.put("errorMessage", "Invalid Name: " + errorMessage);
                    break;
                case "age":
                    response.put("errorMessage", "Invalid Age: " + errorMessage);
                    break;
                case "email":
                    response.put("errorMessage", "Invalid Email: " + errorMessage);
                    break;
                default:
                    response.put("errorMessage", "Validation error on field: " + fieldName);
                    response.put("details", errorMessage);
                    break;
            }
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
