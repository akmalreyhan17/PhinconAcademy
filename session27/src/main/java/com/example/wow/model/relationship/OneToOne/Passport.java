package com.example.wow.model.relationship.OneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Passport {
    @Id
    private Long id;

    private String passportNumber;

    @OneToOne(mappedBy = "passport")
    private Person person;
}
