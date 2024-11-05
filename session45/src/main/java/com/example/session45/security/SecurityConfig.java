package com.example.session45.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final CustomCSRFToken csrfToken;
        private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
        private final CustomAccessDeniedHandler customAccessDeniedHandler;
        private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        public SecurityConfig(CustomCSRFToken csrfToken, CustomAccessDeniedHandler customAccessDeniedHandler,
                        CustomAuthenticationFailureHandler customAuthenticationFailureHandler, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
                this.csrfToken = csrfToken;
                this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
                this.customAccessDeniedHandler = customAccessDeniedHandler;
                this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .anyRequest().authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .csrf(csrf -> csrf
                                                .csrfTokenRepository(csrfToken))
                                .formLogin(login -> login
                                                .failureHandler(customAuthenticationFailureHandler))
                                .exceptionHandling(exception -> exception
                                                .accessDeniedHandler(customAccessDeniedHandler)
                                                .authenticationEntryPoint(customAuthenticationEntryPoint));

                return http.build();
        }
}
