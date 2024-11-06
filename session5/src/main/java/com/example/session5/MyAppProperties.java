package com.example.session5;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "session5")
public class MyAppProperties {
    private String hostName;
    private int port;
    private String from;
}


