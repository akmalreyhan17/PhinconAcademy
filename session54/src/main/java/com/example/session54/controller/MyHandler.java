package com.example.session54.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.session54.model.Order;
import com.example.session54.model.OrderRequest;
import com.example.session54.service.GreetingService;

import reactor.core.publisher.Mono;

@Component
public class MyHandler {

    @Autowired
    GreetingService greetingService;

    public Mono<ServerResponse> streamGreetings(ServerRequest request) {
        return ServerResponse.ok()
                .body(greetingService.streamGreetings(), String.class); // Send Flux<String> as response body
    }

    // Handles a GET request
    public Mono<ServerResponse> getGreeting(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Hello, WebFlux!");
    }

    // Handles a POST request
    public Mono<ServerResponse> createOrder(ServerRequest request) {
        return request.bodyToMono(OrderRequest.class)
                .flatMap(body -> {
                    Order order = new Order();
                    order.setId(body.getId());
                    order.setName(body.getName());
                    return ServerResponse.ok().bodyValue(order);
                });
    }

    public Mono<ServerResponse> personalizedGreeting(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> createGreeting(ServerRequest request) {
        return request.bodyToMono(String.class)
                .flatMap(body -> {
                    if (body.isEmpty()) {
                        return ServerResponse.badRequest().bodyValue("Body cannot be empty!");
                    }
                    return ServerResponse.ok().bodyValue("Created greeting: " + body);
                })
                .onErrorResume(e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("An error occurred: " + e.getMessage()));
    }
}
