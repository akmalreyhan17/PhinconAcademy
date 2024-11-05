package com.example.session14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session14.model.Animal;
import com.example.session14.service.AnimalService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Animal>> getAnimalById(@PathVariable Long id) {
        return animalService.findAnimalById(id).flatMap(result->{
            return Mono.just(ResponseEntity.ok(result));
        });
    }

    @PostMapping
    public Mono<ResponseEntity<Animal>> createAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal).flatMap(result->{
            return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(result));
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Animal>> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.updateAnimal(id, animal).flatMap(result->{
            return Mono.just(ResponseEntity.ok(result));
        });
    }
}
