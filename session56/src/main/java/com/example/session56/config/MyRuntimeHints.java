package com.example.session56.config;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Register proxy interfaces
        hints.proxies().registerJdkProxy(MyServiceInterface.class, AnotherInterface.class);
        
        // Register reflection for a class
        hints.reflection().registerType(MyService.class, builder ->
                builder.withMembers());
    }
}