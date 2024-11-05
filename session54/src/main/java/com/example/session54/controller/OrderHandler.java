package com.example.session54.controller;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class OrderHandler {
    public Mono<ServerResponse> getOrder(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Hello, WebFlux!");
    }
}
