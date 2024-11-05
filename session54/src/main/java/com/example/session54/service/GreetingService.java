package com.example.session54.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class GreetingService {
    public Flux<String> streamGreetings() {
        return Flux.interval(Duration.ofSeconds(1))
                   .map(i -> "Hello, WebFlux! Greeting #" + i);
    }
}
