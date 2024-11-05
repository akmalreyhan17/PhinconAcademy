package com.example.session23.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class OtherService {
    public Flux<String> items() {
        return Flux.just("1", "2");
    }
}
