package com.example.session23.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.session23.model.Animal;
import com.example.session23.service.AnimalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AnimalController.class)
public class AnimalControllerTest {
    @MockBean
    AnimalService animalService;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void shouldGetAnimal() {
        Animal cat = new Animal();
        cat.setId(1L);
        cat.setSpecies("CAT");

        when(animalService.getAnimal(1L)).thenReturn(Mono.just(cat));
        webTestClient
                .get().uri("/animal/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Animal.class)
                .value(animal -> animal.getSpecies().equals("CAT"));
    }

    @Test
    public void shouldSaveAnimal() throws JsonProcessingException {
        Animal cat = new Animal();
        cat.setId(1L);
        cat.setSpecies("CAT");

        Animal dog = new Animal();
        dog.setId(2L);
        dog.setSpecies("DOG");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(List.of(cat,dog));

        when(animalService.createAnimals(any())).thenReturn(Mono.just("Success"));
        webTestClient
                .post().uri("/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(json)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(String.class);
    }
}
