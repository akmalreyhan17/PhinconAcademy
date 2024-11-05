package com.example.session13.model.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer implements Serializable{
    @Id
    private Long id;
    private String name;
    private String email;
    private Integer creditScore;
}


