package com.example.wow.model.relationship.OneToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Parent {
    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Child> children;
}
