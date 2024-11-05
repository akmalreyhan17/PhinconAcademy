package com.example.session56;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class MyTest {
    @Autowired
    private MyService myService;

    @MockBean
    private DependencyService dependencyService;

    @Test
    public void testService() {
        when(dependencyService.getData()).thenReturn("Mocked Data");
        String result = myService.processData();
        assertEquals("Processed Mocked Data", result);
    }
}

