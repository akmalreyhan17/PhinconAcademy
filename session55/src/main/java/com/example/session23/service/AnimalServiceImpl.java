package com.example.session23.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session23.model.Animal;
import com.example.session23.repository.AnimalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Mono<Animal> getAnimal(Long id) {
        return animalRepository.findById(id)
                .switchIfEmpty(Mono.error(new Throwable("Animal not found!")));
    }

    @Override
    public Mono<String> createAnimals(Flux<Animal> animals) {
        return animals.flatMap(animal -> animalRepository.save(animal))
                .count().map(n -> "Success create " + n + " animals");
    }
}
