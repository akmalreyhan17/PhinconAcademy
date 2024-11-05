package com.example.wow.model.relationship.OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Child {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")  // This is the foreign key column
    private Parent parent;
}
