package com.example.session19.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyService {
    public void performAction() {
        log.info("Performing an action...");

        try {
            log.debug("Attempting to perform a complex operation...");
            // Simulate a complex operation
            int result = 1;
            log.info("Operation completed successfully with result: {}", result);
        } catch (Exception e) {
            log.error("An error occurred during operation: {}", e.getMessage(), e);
        }

        log.warn("This is a warning message, something might need attention.");
    }
}
