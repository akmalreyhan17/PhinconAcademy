package com.example.session56.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("this app is run with native image");
    }

    public void loadClassDynamically(String className) {
        try {
            // Class<?> clazz = Class.forName(className);
            // Object instance = clazz.getDeclaredConstructor().newInstance();
            // System.out.println("Loaded: " + instance);

            Object myObject = new Object();

            Class<?> clazz = Class.forName("com.example.MyClass");
            System.out.println("Class name: " + clazz.getName());

            Method method = clazz.getMethod("myMethod");
            method.invoke(myObject);

            Field field = clazz.getDeclaredField("privateField");
            field.setAccessible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
