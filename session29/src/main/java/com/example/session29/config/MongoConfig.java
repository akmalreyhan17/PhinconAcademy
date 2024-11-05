package com.example.session29.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMongoAuditing
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    @Bean
    public MongoClient mongoClient() {

        List<ServerAddress> hosts = List.of(
            new ServerAddress("localhost", 27017),
            new ServerAddress("example.mongodb2.com", 27017),
            new ServerAddress("example.mongodb3.com", 27017)
        );

         MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder -> 
                    builder.hosts(hosts)  // Specify the hosts to connect to
                )
                .applyToConnectionPoolSettings(builder -> 
                    builder.minSize(5)        
                           .maxSize(50)       
                           .maxWaitTime(1000, TimeUnit.MILLISECONDS)  
                )
                .applyToSocketSettings(builder -> 
                    builder.connectTimeout(10, TimeUnit.SECONDS)  
                           .readTimeout(5, TimeUnit.SECONDS)      
                )
                .build();

        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return "myDatabase";  // Specify the database name
    }
}

