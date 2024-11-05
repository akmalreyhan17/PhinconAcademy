package com.example.session54.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.example.session54.controller.MyHandler;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(MyHandler handler) {
        return RouterFunctions
                .route(GET("/greet"), handler::getGreeting)
                .andRoute(POST("/order"), handler::createOrder)
                .filter((request, next) -> {
                    System.out.println("Request path: " + request.path());
                    return next.handle(request);
                });
    }

    @Bean
    public RouterFunction<ServerResponse> router(MyHandler handler) {
        return RouterFunctions
                .route(GET("/greet/{name}"), handler::personalizedGreeting)
                .andRoute(GET("/greet"), handler::getGreeting);
    }

}
