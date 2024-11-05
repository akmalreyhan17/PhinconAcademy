package com.example.session19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session19.model.LoginResponse;
import com.example.session19.model.UserData;
import com.example.session19.model.UserProfile;
import com.example.session19.repository.UserRepository;
import com.example.session19.security.JWTService;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Autowired
    public LoginServiceImpl(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(UserData userData) {
        UserProfile user = userRepository.findByUsername(userData.getUsername()).orElseThrow();
        
        String token = jwtService.generateToken(user);
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setExpiredIn(jwtService.getExpirationTime());
        return response;
    } 
}
