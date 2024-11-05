package com.example.session14.example;

public interface Subscription {
    void request(long n);
    void cancel();
}
