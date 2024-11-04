package com.example;

public class Shared {
    synchronized void waitForSignal() throws InterruptedException {
        System.out.println("Waiting for signal...");
        wait();
        System.out.println("Received signal!");
    }
    synchronized void sendSignal() {
        System.out.println("Sending signal...");
        notify();
    }
}


