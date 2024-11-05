package com.example.session17.model;

public class Account {
    Integer id;
    String username;
    boolean loggedIn;
    Double points;

    void login() {
        // Do login
    }

    Double getPoints() {
        return this.points;
    }
}
