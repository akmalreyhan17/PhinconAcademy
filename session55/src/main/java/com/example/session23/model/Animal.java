package com.example.session23.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Animal implements Persistable<Long> {
    @Id
    Long id;
    String name;
    String species;
    Double weight;

    @Transient
    private Boolean newAnimal = false;

    @Override
    @Transient
    public boolean isNew() {
        return this.newAnimal || id == null;
    }

    public Animal setAsNew() {
        this.newAnimal = true;
        return this;
    }
}
