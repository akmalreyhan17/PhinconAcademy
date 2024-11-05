package com.example.session43.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User delegate;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomOAuth2User(OAuth2User delegate, Set<SimpleGrantedAuthority> authorities2) {
        this.delegate = delegate;
        this.authorities = authorities2;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return delegate.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    public String getEmail() {
        return delegate.<String>getAttribute("email");
    }

}
