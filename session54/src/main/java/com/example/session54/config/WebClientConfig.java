package com.example.session54.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://your-api.com")
                .defaultHeader("User-Agent", "YourCustomClient")
                .build();
    }

    @Bean
    WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations) {

        InMemoryReactiveOAuth2AuthorizedClientService clientService = 
                        new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrations);

        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager = 
                        new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrations, clientService);

        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = 
                        new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

        oauth.setDefaultClientRegistrationId("google");

        return WebClient.builder()
                .filter(oauth)
                .build();
    }
}
