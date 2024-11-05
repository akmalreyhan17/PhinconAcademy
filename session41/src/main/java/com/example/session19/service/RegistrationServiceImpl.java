package com.example.session19.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.session19.model.UserData;
import com.example.session19.model.UserProfile;
import com.example.session19.repository.UserRepository;
import com.example.session19.security.Role;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(UserData data) {
        log.info("get to service");
        Optional<UserProfile> user = userRepository.findByUsername(data.getUsername());
        if (user.isPresent()) {
            return false;
        }
        UserProfile newUser = new UserProfile();
        newUser.setUsername(data.getUsername());
        newUser.setPassword(passwordEncoder.encode(data.getPassword()));
        newUser.setRole(Role.ROLE_USER);
        userRepository.save(newUser);
        return true;
    }
}
