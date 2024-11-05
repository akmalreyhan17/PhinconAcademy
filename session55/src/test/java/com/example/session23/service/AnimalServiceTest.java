package com.example.session23.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.session23.model.Animal;
import com.example.session23.repository.AnimalRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {
    @Mock
    AnimalRepository animalRepository;

    @InjectMocks
    AnimalServiceImpl animalService;

    @Test
    public void testAnimalGetPositive() {
        Animal cat = new Animal();
        cat.setId(1L);
        cat.setSpecies("CAT");

        when(animalRepository.findById(anyLong())).thenReturn(Mono.just(cat));
        Mono<Animal> foundAnimal = animalService.getAnimal(1L);

        StepVerifier.create(foundAnimal)
                .expectNextMatches(animal -> animal.getSpecies().equals("CAT"))
                .expectComplete()
                .verify();
    }

    @Test
    public void testAnimalGetNegative() {
        Animal cat = new Animal();
        cat.setId(1L);
        cat.setSpecies("CAT");

        when(animalRepository.findById(anyLong())).thenReturn(Mono.empty());
        Mono<Animal> foundAnimal = animalService.getAnimal(1L);

        StepVerifier.create(foundAnimal)
                .expectErrorMessage("Animal not found!")
                .verify();
    }

    @Test
    public void testAnimalCreate() {
        Animal cat = new Animal();
        cat.setId(1L);
        cat.setSpecies("CAT");

        Animal dog = new Animal();
        dog.setId(2L);
        dog.setSpecies("DOG");

        TestPublisher<Animal> animals = TestPublisher.create();
        Mono<String> result = animalService.createAnimals(animals.flux());

        when(animalRepository.save(cat)).thenReturn(Mono.just(cat));
        when(animalRepository.save(dog)).thenReturn(Mono.just(dog));

        StepVerifier.create(result)
                .then(() -> animals.emit(cat, dog))
                .expectNext("Success create 2 animals")
                .expectComplete()
                .verify();
    }
}
