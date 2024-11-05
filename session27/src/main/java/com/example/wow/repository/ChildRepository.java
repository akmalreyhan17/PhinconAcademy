package com.example.wow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.wow.model.relationship.OneToMany.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    
}
