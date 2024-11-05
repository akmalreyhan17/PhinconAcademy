package com.example.session45.security;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomCSRFToken implements CsrfTokenRepository{

    private static final String CSRF_TOKEN_HEADER_NAME = "X-CSRF-TOKEN";
    private static final String CSRF_PARAMETER_NAME = "_csrf";
    
    private final ConcurrentHashMap<String, CsrfToken> tokenStore = new ConcurrentHashMap<>();

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String tokenValue = java.util.UUID.randomUUID().toString();
        return new DefaultCsrfToken(CSRF_TOKEN_HEADER_NAME, CSRF_PARAMETER_NAME, tokenValue);
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        return tokenStore.get(request.getSession().getId());
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (token == null) {
            tokenStore.remove(request.getSession().getId());
        } else {
            tokenStore.put(request.getSession().getId(), token);
        }
    }
    
}


