package com.example.wow.model.relationship.ManyToMany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Student {
    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",                                // The name of the join table
        joinColumns = @JoinColumn(name = "student_id"),         // Column from this entity
        inverseJoinColumns = @JoinColumn(name = "course_id")    // Column from the other entity
    )
    private Set<Course> courses;
}
