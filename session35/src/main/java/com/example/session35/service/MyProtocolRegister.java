package com.example.session35.service;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class MyProtocolRegister implements ResourceLoaderAware {

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        if (resourceLoader instanceof DefaultResourceLoader) {
            DefaultResourceLoader defaultResourceLoader = (DefaultResourceLoader) resourceLoader;
            defaultResourceLoader.addProtocolResolver(new MyProtocol());
        } else {
            throw new IllegalStateException("ResourceLoader is not a DefaultResourceLoader");
        }
    }
    
}
