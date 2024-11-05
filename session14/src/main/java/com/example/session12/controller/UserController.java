package com.example.session12.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.session12.model.User;
import com.example.session12.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/client-script.js")
    public ResponseEntity<String> getClientScript() {
        String script = "console.log('Hello from the server!');";
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(script);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/users/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return userService.patchUser(id, updates);
    }

    @GetMapping("/greet")
    public String greet(@RequestHeader("User-Agent") String userAgent) {
        return "Hello, your user agent is " + userAgent;
    }

    @GetMapping("/custom-header")
    public ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomValue");
        return new ResponseEntity<>("Hello World", headers, HttpStatus.OK);
    }

}
