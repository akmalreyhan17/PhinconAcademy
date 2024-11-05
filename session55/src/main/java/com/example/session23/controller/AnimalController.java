package com.example.session23.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session23.model.Animal;
import com.example.session23.service.AnimalService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Animal>> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimal(id).flatMap(result->{
            return Mono.just(ResponseEntity.ok(result));
        });
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createAnimals(@RequestBody List<Animal> animals) {
        Flux<Animal> animalData = Flux.fromIterable(animals);
        return animalService.createAnimals(animalData).flatMap(result->{
            return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(result));
        });
    }
}
