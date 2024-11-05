package com.example;

public class Woman extends Human {
    void accessMethods() {
        eat();     // Public
        sleep();   // Protected
        run();     // Default
        //breathe(); // Private, not accessible
    }
}


