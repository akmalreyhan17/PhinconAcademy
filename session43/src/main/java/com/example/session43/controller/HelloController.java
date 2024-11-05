package com.example.session43.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session43.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class HelloController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public String getRefreshToken(OAuth2AuthenticationToken authentication) {
        String clientRegistrationId = authentication.getAuthorizedClientRegistrationId();
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(clientRegistrationId,
                authentication.getName());
        return client.getRefreshToken().getTokenValue();
    }

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal OAuth2User user) {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient("google", user.getName());

        String accessToken = client.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken);

        return "Hello, " + user.getAttribute("name");
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }

    @GetMapping("/auth")
    public String authenticated() {
        return "Welcome authenticated user!";
    }

    @GetMapping("/info")
    public String info(@AuthenticationPrincipal OAuth2User user) throws JsonMappingException, JsonProcessingException {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient("google", user.getName());

        String accessToken = client.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken);

        return userService.fetchGoogleUserProfile(accessToken);
    }

    @GetMapping("/login-success")
    public ResponseEntity<?> loginSuccess(
            @RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        String refreshToken = authorizedClient.getRefreshToken().getTokenValue();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        System.out.println("Refresh Token: " + refreshToken);

        return ResponseEntity.ok().headers(headers).body("Login Successful!");
    }
}
