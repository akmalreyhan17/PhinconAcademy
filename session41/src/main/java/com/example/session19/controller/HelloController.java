package com.example.session19.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/home")
    public String home() {
        return "Welcome to the home page!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/authenticated")
    public String authenticated() {
        return "You are already authenticated!";
    }
}
