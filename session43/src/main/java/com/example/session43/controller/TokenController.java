package com.example.session43.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session43.model.User;
import com.example.session43.service.JwtService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class TokenController {

    @Autowired
    JwtService jwtService;

    @GetMapping("/generate-token")
    public ResponseEntity<String> generateToken(@AuthenticationPrincipal User authentication) {
        if (authentication == null) {;
            return new ResponseEntity<String>("Unauthorized", HttpStatusCode.valueOf(401));
        }

        // Generate JWT using user details
        String jwt = jwtService.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }
}
