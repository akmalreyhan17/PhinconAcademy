package com.example.session48.schedule;

import org.springframework.stereotype.Component;

@Component
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Executing task " + name + " in thread " + Thread.currentThread().getName());
    }
}
