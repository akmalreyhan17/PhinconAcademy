package com.example.session12.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.session12.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
