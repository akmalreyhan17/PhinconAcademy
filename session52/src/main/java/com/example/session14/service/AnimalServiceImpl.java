package com.example.session14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session14.model.Animal;
import com.example.session14.repository.AnimalRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Override
    public Flux<Animal> getAnimals() {

        animalRepository.findById(1L).flatMap(myAnimal -> {
            myAnimal.setName("Mickey");
            return animalRepository.save(myAnimal);
        }).subscribe(animal -> {
            System.out.println(animal.getName());
        });

        return animalRepository.findAll();
    }

    @Override
    public Mono<Animal> getAnimal(Long id) {

        Mono<Animal> panda = Mono.just(new Animal("Panda"));
        panda.map(myPanda -> {
            myPanda.setName("Hu Bao");
            myPanda.setWeight(100.0 / 0);
            return myPanda;
        }).onErrorReturn(new Animal("Cat"))
        .map(myPanda -> {
            myPanda.setId(99L);
            return myPanda;
        });

        // Animal myPanda = panda.block();
        // myPanda.setName("Hu Bao");

        Flux<Animal> herbivore = Flux.just(new Animal("Cow"), new Animal("Giraffe"));
        herbivore.flatMap(myHerbivore->{
            String species = myHerbivore.getSpecies();
            return Flux.just(new Animal(species, "A"), new Animal(species, "B"));
        }).map(myAnimal -> {
            myAnimal.setWeight(100.0);
            return myAnimal;
        });

        Mono<Animal> lion = Mono.empty();
        Flux<Animal> carnivore = Flux.empty();

        return animalRepository.findById(id);
    }

    @Override
    public Mono<Animal> findAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public Mono<Animal> createAnimal(Animal animal) {
        animal.setAsNew();
        return animalRepository.save(animal);
    }

    @Override
    public Mono<Animal> updateAnimal(Long id, Animal animal) {
        return animalRepository.findById(id).flatMap(foundAnimal -> {
            foundAnimal.setName(animal.getName());
            foundAnimal.setSpecies(animal.getSpecies());
            foundAnimal.setWeight(animal.getWeight());
            return animalRepository.save(foundAnimal);
        });
    }

    

}
