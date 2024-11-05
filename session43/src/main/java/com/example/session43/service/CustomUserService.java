package com.example.session43.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.session43.config.CustomOAuth2User;
import com.example.session43.model.Role;
import com.example.session43.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
public class CustomUserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
                
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Extract email from user attributes
        String email = oAuth2User.getAttribute("email");
        User user = new User();

        // Assign roles based on email
        if (email != null) {
            user.setUsername(email);
            if (email.endsWith("@admin.com")) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
                ;
            }
        }

        return user;
    }
}
