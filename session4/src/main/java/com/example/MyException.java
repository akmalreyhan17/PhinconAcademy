package com.example;

public class MyException extends Exception {
    public MyException(int code, String message) {
        super(String.valueOf(code) + ": " + message);
    }
}


