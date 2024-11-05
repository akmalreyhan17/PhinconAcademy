package com.example.session48.schedule;

import java.util.concurrent.Callable;

public class MyCallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "Task completed in: " + Thread.currentThread().getName();
    }
    
}
