package com.example.session35.service;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class MyClass implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void loadResource() {
        Resource resource = resourceLoader.getResource("classpath:application.properties");
        System.out.println("Resource exists: " + resource.exists());
    }
    
}
