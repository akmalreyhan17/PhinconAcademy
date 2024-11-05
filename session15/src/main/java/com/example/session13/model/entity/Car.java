package com.example.session13.model.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Car implements Serializable {
    @Id
    private Long id;
    private String model;
    private String brand;
    private Double price;
}


