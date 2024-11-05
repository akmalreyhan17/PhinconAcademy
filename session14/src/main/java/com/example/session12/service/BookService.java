package com.example.session12.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session12.model.Book;
import com.example.session12.repository.BookRepository;

@Service
public class BookService implements BookInterface {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book saveBook(Book book) {
        return new Book();
    }

    @Override
    public List<Book> findAllBooks() {
        return new ArrayList<Book>();
    }
}
