package com.example.session7;

import org.springframework.stereotype.Service;

@Service
public class MyService {

    @TrackTime
    public void printName(String name) {
        System.out.println(name);
    }
    
}


