package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandlingExample {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(
            new FileWriter("output.txt"));
        writer.write("Hello, World!");
        writer.close();

        BufferedReader reader = new BufferedReader(
            new FileReader("output.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }
}


