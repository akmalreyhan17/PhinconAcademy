package com.example.session23.service;

import org.springframework.stereotype.Service;

import com.example.session23.model.Animal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface AnimalService {
    Mono<Animal> getAnimal(Long id);
    Mono<String> createAnimals(Flux<Animal> animals);
}
