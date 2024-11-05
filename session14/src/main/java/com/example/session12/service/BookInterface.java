package com.example.session12.service;

import java.util.List;

import com.example.session12.model.Book;

public interface BookInterface {
    Book findBookById(Long id);
    Book saveBook(Book book);
    List<Book> findAllBooks();
}


