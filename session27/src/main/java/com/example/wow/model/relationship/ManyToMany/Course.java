package com.example.wow.model.relationship.ManyToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Course {
    @Id
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
