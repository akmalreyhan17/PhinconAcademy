package com.example.session26.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "User API",
        version = "v1",
        description = "API for managing users"
    )
)
public class OpenAPIConfig {
    
}
