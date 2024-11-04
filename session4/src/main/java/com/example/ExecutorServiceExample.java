package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 0; i < 5; i++) {
            executor.submit(new Task());
        }
        
        executor.shutdown();
    }
}

class Task implements Runnable {
    public void run() {
        System.out.println("Task executed by " + Thread.currentThread().getName());
    }
}


