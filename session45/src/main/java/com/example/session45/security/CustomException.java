package com.example.session45.security;

import java.io.IOException;

public class CustomException extends IOException {
    private String message;
    public CustomException(String message) {
        this.message = message;
    }
}
