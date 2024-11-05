package com.example;

public class MyPet implements Dog, Cat {
    @Override
    public void pet() {
        Dog.super.pet();
        Cat.super.pet();
    }
}


