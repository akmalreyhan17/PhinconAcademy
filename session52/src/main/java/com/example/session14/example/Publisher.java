package com.example.session14.example;

import org.reactivestreams.Subscriber;

public interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}


