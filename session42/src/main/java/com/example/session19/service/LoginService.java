package com.example.session19.service;

import org.springframework.stereotype.Service;

import com.example.session19.model.LoginResponse;
import com.example.session19.model.UserData;

@Service
public interface LoginService {
    LoginResponse login(UserData userData);
}
