package com.example.session9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.session9.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);

    @Query("SELECT u FROM Student u WHERE u.email = ?1")
    Student findByEmail(String email);

}


