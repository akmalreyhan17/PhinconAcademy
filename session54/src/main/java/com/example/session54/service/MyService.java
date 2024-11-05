package com.example.session54.service;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.springframework.http.ResponseCookie;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.example.session54.model.Order;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

public class MyService {

    private String accessToken = "your-access-token";

    WebClient customWebClient = WebClient.builder()
            .baseUrl("https://your-api.com")
            .defaultHeader("User-Agent", "YourCustomClient")
            .defaultCookie("sessionId", "abc123")
            .defaultHeader("Authorization", "Bearer " + accessToken)
            .build();

    WebClient webClient = WebClient.create("https://my-api.com");

    public void get() {
        Mono<String> response = webClient.get()
                .uri("/get-endpoint")
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(res -> System.out.println("Response: " + res));
    }

    public void getAll() {
        Flux<Order> response = webClient.get()
                .uri("/get-all-endpoint")
                .retrieve()
                .bodyToFlux(Order.class);

        response
                .flatMap(order -> {
                    order.setName("new " + order.getName());
                    return Mono.just(order);
                })
                .subscribe(res -> System.out.println("Response: " + res.getName()));
    }

    public void post() {
        Mono<String> response = webClient.post()
                .uri("/post-endpoint")
                .header("Content-Type", "application/json")
                .bodyValue("{\"name\":\"YourName\", \"message\":\"Hello, world!\"}")
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(res -> System.out.println("Response: " + res));
    }

    public void put() {
        Mono<String> response = webClient.put()
                .uri("/put-endpoint")
                .header("Content-Type", "application/json")
                .bodyValue("{\"name\":\"UpdatedName\", \"message\":\"Updated message!\"}")
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(res -> System.out.println("Response: " + res));
    }

    public void delete() {

        Mono<String> response = webClient.delete()
                .uri("/delete-endpoint")
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(res -> System.out.println("Response: " + res));
    }

    public void exchange() {

        Mono<String> response = webClient.get()
                .uri("/get-endpoint")
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(String.class);
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return Mono.error(new RuntimeException("Client error!"));
                    } else {
                        return Mono.error(new RuntimeException("Unexpected error!"));
                    }
                });

        response.subscribe(result -> System.out.println("Response: " + result));
    }

    public void pagination() {

        Mono<String> response = webClient.get()
                .uri("/data-with-pagination")
                .exchangeToMono(clientResponse -> {
                    List<String> paginationInfo = clientResponse.headers().header("X-Total-Count");
                    System.out.println("Total items: " + paginationInfo.get(0));
                    return clientResponse.bodyToMono(String.class);
                });

        response.subscribe(result -> System.out.println("Response: " + result));
    }

    public void auth() {

        Mono<String> response = webClient.post()
                .uri("/login")
                .bodyValue("{\"username\":\"yourUsername\", \"password\":\"yourPassword\"}")
                .exchangeToMono(clientResponse -> {
                    String accessToken = clientResponse.headers().asHttpHeaders().getFirst("Authorization");
                    System.out.println("Access Token: " + accessToken);
                    return clientResponse.bodyToMono(String.class);
                });

        response.subscribe(result -> System.out.println("Response: " + result));
    }

    public void cookie() {

        Mono<String> response = webClient.get()
                .uri("/auth-required")
                .exchangeToMono(clientResponse -> {
                    Map<String, ResponseCookie> cookies = clientResponse.cookies().toSingleValueMap();
                    cookies.forEach(
                            (name, cookie) -> System.out.println("Cookie: " + name + " = " + cookie.getValue()));
                    return clientResponse.bodyToMono(String.class);
                });

        response.subscribe(result -> System.out.println("Response: " + result));
    }

    public void csrf() {

        Mono<String> response = webClient.get()
                .uri("/csrf-token-endpoint")
                .exchangeToMono(clientResponse -> {
                    ResponseCookie csrfTokenCookie = clientResponse.cookies().getFirst("XSRF-TOKEN");
                    if (csrfTokenCookie != null) {
                        String csrfToken = csrfTokenCookie.getValue();
                        System.out.println("CSRF Token: " + csrfToken);
                    }
                    return clientResponse.bodyToMono(String.class);
                });

        response.subscribe(result -> System.out.println("Response: " + result));
    }

    public void err() {

        Mono<String> response = webClient.get()
                .uri("/possibly-error-endpoint")
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is4xxClientError()) {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Client error: " + errorBody)));
                    } else if (clientResponse.statusCode().is5xxServerError()) {
                        return Mono.error(new RuntimeException("Server error occurred!"));
                    }
                    return clientResponse.bodyToMono(String.class);
                });

        response.subscribe(result -> System.out.println("Response: " + result));
    }

    public void connection() {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(10))
                        .addHandlerLast(new WriteTimeoutHandler(10)))
                .responseTimeout(Duration.ofSeconds(5));

        WebClient customWebClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        System.out.println(customWebClient);
    }

    public void errhand() {

        WebClient customWebClient = WebClient.builder()
                .baseUrl("https://your-api.com")
                .filter((request, next) -> next.exchange(request)
                        .flatMap(response -> {
                            if (response.statusCode().isError()) {
                                return response.bodyToMono(String.class)
                                        .flatMap(errorBody -> Mono.error(new RuntimeException(
                                                "Request failed: " + response.statusCode() + " - " + errorBody)));
                            }
                            return Mono.just(response);
                        }))
                .build();

        System.out.println(customWebClient);
    }

    public static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            System.out.println("Response Status: " + clientResponse.statusCode());
            return Mono.just(clientResponse); // Continue the response processing
        });
    }

    public void customWebClient() {

        WebClient customWebClient = WebClient.builder()
                .baseUrl("https://your-api.com")
                .filter(logRequest()) // Log requests
                .filter(logResponse()) // Log responses
                .filter(handleErrors()) // Handle errors
                .filter(customHeader()) // Add a custom header
                .build();

        System.out.println(customWebClient);
    }

    private static ExchangeFilterFunction customHeader() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'customHeader'");
    }

    private static ExchangeFilterFunction handleErrors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleErrors'");
    }

    private static ExchangeFilterFunction logRequest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logRequest'");
    }

    public void retry() {

        WebClient webClient = WebClient.create("https://unstable-api.com");

        Mono<String> response = webClient.get()
                .uri("/endpoint")
                .retrieve()
                .bodyToMono(String.class)
                .retry(3); // Retry up to 3 times

        System.out.println(response);
    }

    public void retrywhen() {
        WebClient webClient = WebClient.create("https://unstable-api.com");

        Mono<String> response = webClient.get()
                .uri("/endpoint")
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(1)) // Retry 3 times, starting with 1-second backoff
                                .maxBackoff(Duration.ofSeconds(5)) // Set a maximum backoff delay
                                .jitter(0.2) // Add jitter for randomized backoff (20% variation)
                                .doBeforeRetry(signal -> System.out.println("Retrying due to: " + signal.failure())) // Log
                );

        System.out.println(response);
    }

    public void retryagain() {

        Mono<String> response = webClient.get()
                .uri("/endpoint")
                .retrieve()
                .bodyToMono(String.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofSeconds(1))
                                .filter(throwable -> {
                                    if (throwable instanceof WebClientResponseException) {
                                        WebClientResponseException ex = (WebClientResponseException) throwable;
                                        return ex.getStatusCode().is5xxServerError(); // Only retry on server errors
                                    }
                                    return false;
                                }));

        System.out.println(response);
    }

    public WebClient ssl() throws SSLException {
        // Load the custom trust store
        File trustStoreFile = Paths.get("path/to/truststore.jks").toFile();

        SslContext sslContext = SslContextBuilder.forClient()
            .trustManager(trustStoreFile)
            .build(); 

        HttpClient httpClient = HttpClient.create()
            .secure(sslSpec -> sslSpec.sslContext(sslContext));

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }

    public void blocking() {
        
        WebClient webClient = WebClient.create("https://your-api.com");

        String response = webClient.get()
            .uri("/endpoint")
            .retrieve()
            .bodyToMono(String.class)
            .block();  // This will block until the response is received

        System.out.println("Response: " + response);
    }
}
