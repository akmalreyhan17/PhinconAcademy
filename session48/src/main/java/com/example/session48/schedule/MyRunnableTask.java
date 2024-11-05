package com.example.session48.schedule;

public class MyRunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Executing task in: " + Thread.currentThread().getName());
    }
}
