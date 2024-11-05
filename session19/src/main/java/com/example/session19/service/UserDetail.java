package com.example.session19.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.session19.model.UserProfile;
import com.example.session19.repository.UserRepository;

@Service
public class UserDetail implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserProfile> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            new UsernameNotFoundException("user" + username + "not exists");
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.get().getRole().toString());
        return User.builder()
                .username(username)
                .password(user.get().getPassword())
                .authorities(grantedAuthority)
                .build();
    }
}
