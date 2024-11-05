package com.example.wow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wow.model.relationship.OneToMany.Parent;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Parent findByName(String name);
}
