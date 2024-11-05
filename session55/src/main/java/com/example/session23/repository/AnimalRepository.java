package com.example.session23.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.session23.model.Animal;

@Repository
public interface AnimalRepository extends R2dbcRepository<Animal, Long> {
    
}
