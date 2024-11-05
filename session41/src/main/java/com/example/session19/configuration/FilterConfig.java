package com.example.session19.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.session19.security.APIKeyFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<APIKeyFilter> apiKeyFilter(){
        FilterRegistrationBean<APIKeyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new APIKeyFilter());
        registrationBean.addUrlPatterns("/hello"); // Protect specific URL patterns
        return registrationBean;
    }
}
