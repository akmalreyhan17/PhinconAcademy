package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Employee employee = new Programmer();
        employee.work(); // Print "Programming new code."

        employee = new SalesPerson();
        employee.work(); // Print "Searching new client."

        Employee[] employees = { new Programmer(), new SalesPerson() };
        for (Employee employee2 : employees) {
            employee2.work();
            // Print "Programming" then "Searching client"
        }

        FileInputStream input = new FileInputStream("input.txt");
        FileOutputStream output = new FileOutputStream("output.txt");

        int data = input.read();
        while (data != -1) {
            output.write(data);
            data = input.read();
        }

        input.close();
        output.close();

        FileReader reader = new FileReader("input.txt");
        FileWriter writer = new FileWriter("output.txt");

        int data2 = reader.read();
        while (data2 != -1) {
            writer.write(data2);
            data2 = reader.read();
        }

        reader.close();
        writer.close();

    }
}
