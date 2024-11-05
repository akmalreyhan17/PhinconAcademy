package com.example.session14.service;

import org.springframework.stereotype.Service;

import com.example.session14.model.Animal;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AnimalService {
    Flux<Animal> getAnimals();
    Mono<Animal> getAnimal(Long id);
    Mono<Animal> findAnimalById(Long id);
    Mono<Animal> createAnimal(Animal animal);
    Mono<Animal> updateAnimal(Long id, Animal animal);
}
