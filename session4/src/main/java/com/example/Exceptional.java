package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Exceptional {
    // Checked Exception
    public void readFile() {

        try {
            FileReader file = new FileReader("file.txt");
        } 
        
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Unchecked Exception
    public void divide(int a, int b) {
    
        try {
            int data = a / b;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        } finally {
            System.out.println("Divide finished");
        }
    
    }    

    public void ageValidation(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18");
        }
    }

    int[] array = {1, 2, 3};

    public void getArrayElement(int index) {
        try {
            System.out.println("element: " + array[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds");
        } catch (Exception e) {
            System.out.println("General error");
        }
    }
    
    int[] array2 = {0, 1, 2};

    public void divide2(int a, int b, int index) {
    
        try {
            int divider = array2[index];
            try {
                int data = 50 / divider;
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of bounds");
        } 
    
    }

    public void divide3() {

        try {
            int num = Integer.parseInt("abc");
            int data = 50 / 0;
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Exception: " + e);
        }
        
    }
    
}

