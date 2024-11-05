package com.example.session17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.session17.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
