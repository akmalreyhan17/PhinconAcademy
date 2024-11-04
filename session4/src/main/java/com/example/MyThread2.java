package com.example;

public class MyThread2 extends Thread {
    Shared shared;
    String code;
    public void run() {
        if (code.equals("send")) {
            shared.sendSignal();
        } else {
            try {
                shared.waitForSignal();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    MyThread2(Shared shared, String code) {
        this.shared = shared;
        this.code = code;
    }
}


