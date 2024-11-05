package com.example.session19.service;

import org.springframework.stereotype.Service;

import com.example.session19.model.UserData;

@Service
public interface RegistrationService {
    boolean registerUser(UserData data);
}
