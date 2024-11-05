package com.example.session23.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MyServiceTest {
    @MockBean
    OtherService otherService;

    @Autowired
    MyService myService;

    @Test
    public void testMyService() {
        when(otherService.items()).thenReturn(Flux.just("1","2"));
        Flux<String> items = myService.getItem();
        
        StepVerifier.create(items)
                .expectNext("Item: 1", "Item: 2")
                .expectNextCount(6)
                .expectComplete()
                .verify();
    }
}
