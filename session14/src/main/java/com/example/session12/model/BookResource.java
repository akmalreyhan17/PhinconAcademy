package com.example.session12.model;

import org.springframework.hateoas.RepresentationModel;

public class BookResource extends RepresentationModel<BookResource>{
    private final Book book;

    public BookResource(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}


