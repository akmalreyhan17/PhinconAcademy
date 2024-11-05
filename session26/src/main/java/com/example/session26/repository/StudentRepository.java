package com.example.session26.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.session26.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
