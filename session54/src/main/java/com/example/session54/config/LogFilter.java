package com.example.session54.config;

import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;

import reactor.core.publisher.Mono;

public class LogFilter implements ExchangeFilterFunction {
    
    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        // Modify request
        ClientRequest modRequest = ClientRequest.from(request)
                .header("Auth", "Bearer token")
                .attribute("name", "default")
                .build();

        // Modify response
        return next
                .exchange(modRequest)
                .flatMap(clientResponse -> {
                    System.out.println("Response Status: " + clientResponse.statusCode());
                    return Mono.just(clientResponse); // Continue the response processing
                });
    }
}
