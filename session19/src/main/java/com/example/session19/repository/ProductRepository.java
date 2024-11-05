package com.example.session19.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.session19.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
