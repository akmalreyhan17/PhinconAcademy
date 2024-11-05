package com.example.wow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wow.model.relationship.OneToMany.Child;
import com.example.wow.model.relationship.OneToMany.Parent;
import com.example.wow.repository.ChildRepository;
import com.example.wow.repository.ParentRepository;
import com.example.wow.repository.RepositoryJPA;

@Service
public class MyService {
    @Autowired
    ParentRepository parentRepository;

    @Autowired
    ChildRepository childRepository;

    @Autowired
    RepositoryJPA repository;

    public void saveChildren() {
        // Fetch the existing Parent entity
        Parent parent = parentRepository.findByName("John");

        Child child = parent.getChildren().stream()
                .filter(n -> n.getName().startsWith("A"))
                .findAny().orElse(null);

        // Create a new Child and associate it with the Parent
        // Child child = new Child();
        // child.setName("Anna");
        // child.setParent(parent); // Setting the parent reference

        // Save the Child entity
        childRepository.save(child);

        repository.findByNameLike("John%");

        
    }
}
