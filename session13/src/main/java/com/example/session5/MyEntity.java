package com.example.session5;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;

@Entity
public class MyEntity {
    @Id
    private Integer id;
    private String name;
}


