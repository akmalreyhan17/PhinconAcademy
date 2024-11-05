package com.example.session7;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long userId) {
        // Business logic to delete user
        System.out.println("User deleted: " + userId);
    }

    @PreAuthorize("hasRole('USER')")
    public Account getUserDetails(Long userId) {
        // Business logic to get user details
        return new Account(userId, "John Doe");
    }
}



