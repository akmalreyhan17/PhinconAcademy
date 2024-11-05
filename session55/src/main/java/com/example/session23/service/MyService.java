package com.example.session23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MyService {

    @Value("${repeat.count}")
    Long count;
    
    @Autowired
    OtherService otherService;

    public Flux<String> getItem() {
        return otherService.items().flatMap(item -> {
            return Mono.just("Item: " + item);
        }).repeat(count);
    }
}
