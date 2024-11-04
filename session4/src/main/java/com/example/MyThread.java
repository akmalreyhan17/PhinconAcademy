package com.example;

public class MyThread extends Thread{
    String name;
    Counter counter;
    public void run() {
        System.out.println("Thread " + name + " is running");
        counter.increment();
    }

    MyThread(Counter counter, String name) {
        this.name = name;
        this.counter = counter;
    }
}


