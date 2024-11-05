package com.example.session53.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.session53.advice.CustomException;
import com.example.session53.model.Order;
import com.example.session53.model.Product;
import com.example.session53.model.User;
import com.example.session53.service.MyService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
public class MyController {

    @Autowired
    MyService orderService;

    @GetMapping("/hello")
    public Mono<String> sayHello() {
        return Mono.error(new RuntimeException("Oops, something went wrong!"));
    }

    // @GetMapping("/user/{id}")
    // public Mono<User> getUser(@PathVariable String id) {
    // return userService.findById(id)
    // .onErrorResume(e -> Mono.just(new User("default", "user"))); // Fallback in
    // case of error
    // }

    // @GetMapping("/product/{id}")
    // public Mono<Product> getProduct(@PathVariable String id) {
    // return productService.findById(id)
    // .onErrorReturn(new Product("default", "product")); // Return default product
    // on error
    // }

    @GetMapping("/order/{id}")
    public Mono<Order> getOrder(@PathVariable String id) {
        return orderService.findById(id)
                .onErrorMap(e -> new CustomException("Failed to fetch order", e)); // Wrap and rethrow
    }

    @PostMapping("/user")
    public Mono<ResponseEntity<String>> createUser(@Valid @RequestBody Mono<User> userMono) {
        return userMono
                .map(user -> ResponseEntity.ok("User created successfully"))
                .onErrorResume(MethodArgumentNotValidException.class, e -> {
                    return Mono.just(ResponseEntity.badRequest().body("Validation failed: " + e.getMessage()));
                });
    }
}
