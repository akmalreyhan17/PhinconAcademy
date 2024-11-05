package com.example.wow.model.relationship.OneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    private Long id;
    
    private String name;

    @OneToOne
    @JoinColumn(name = "passport_id")  // This is the foreign key column
    private Passport passport;
}
