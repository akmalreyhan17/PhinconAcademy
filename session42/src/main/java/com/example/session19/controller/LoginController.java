package com.example.session19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.session19.model.LoginResponse;
import com.example.session19.model.UserData;
import com.example.session19.service.LoginService;

@RestController
public class LoginController {
    
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserData userData) {
        LoginResponse response = loginService.login(userData);
        return ResponseEntity.ok().body(response);
    }
}
