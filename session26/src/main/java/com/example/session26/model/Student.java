package com.example.session26.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @Schema(description = "Unique identifier of the user", example = "1")
    private Integer id;

    @Schema(description = "Name of the user", example = "John Doe")
    private String name;
    private Double gpa;
}
