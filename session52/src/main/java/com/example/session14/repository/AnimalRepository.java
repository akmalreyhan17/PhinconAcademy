package com.example.session14.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.example.session14.model.Animal;

import reactor.core.publisher.Mono;

public interface AnimalRepository extends R2dbcRepository<Animal, Long>{
    Mono<Animal> findByName();
}
