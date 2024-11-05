package com.example.session19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.session19.model.UserData;
import com.example.session19.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserData userData) {
        log.info("get to controller");
        Boolean result = registrationService.registerUser(userData);
        if (result) {
            return ResponseEntity.ok().body("Succes created user: " + userData.getUsername());
        }
        return ResponseEntity.ok().body("User already exists!");
    }
}
