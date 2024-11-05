package com.example.session54.controller;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class UserHandler {
    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        return ServerResponse.ok().build();
    }
}
