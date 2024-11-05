package com.example.session56.model;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@RegisterReflectionForBinding
public class MyEntity {
    @Id
    private Long id;
    private String name;
}
