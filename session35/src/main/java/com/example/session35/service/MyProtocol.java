package com.example.session35.service;

import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MyProtocol implements ProtocolResolver {

    private static final String PROTOCOL_PREFIX = "myproc:";

    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith(PROTOCOL_PREFIX)) {
            return new MyResource(location.substring(PROTOCOL_PREFIX.length()));
        }
        return null; // Return null if the protocol is not matched
    }
    
}
