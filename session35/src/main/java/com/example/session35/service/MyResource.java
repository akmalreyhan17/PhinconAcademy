package com.example.session35.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.AbstractResource;

public class MyResource extends AbstractResource {

    private final String location;

    public MyResource(String location) {
        this.location = location;
    }

    @Override
    public String getDescription() {
        return "My resource [" + location + "]";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        byte[] data = loadData(location);
        return new ByteArrayInputStream(data);
    }

    private byte[] loadData(String location2) {
        return "Loaded data".getBytes();
    }
    
}
