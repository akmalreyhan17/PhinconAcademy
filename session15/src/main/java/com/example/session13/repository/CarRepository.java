package com.example.session13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.session13.model.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    
}
