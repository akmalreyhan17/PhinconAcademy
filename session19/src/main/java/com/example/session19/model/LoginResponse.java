package com.example.session19.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Long expiredIn;
}
