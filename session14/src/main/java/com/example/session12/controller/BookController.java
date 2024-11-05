package com.example.session12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session12.model.Book;
import com.example.session12.model.BookResource;
import com.example.session12.model.User;
import com.example.session12.service.BookInterface;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookInterface bookInterface;

    // @GetMapping("/{id}")
    // public ResponseEntity<BookResource> getBookById(@PathVariable Long id) {
    // Book book = bookInterface.findBookById(id);
    // if (book != null) {
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_JSON);

    // BookResource resource = new BookResource(book);
    // resource.add(linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel());
    // resource.add(linkTo(methodOn(BookController.class).getAllBooks("")).withRel("all-books"));

    // return new ResponseEntity<>(resource, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(bookInterface.findBookById(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@RequestHeader("Authorization") String authToken) {
        if (isValidToken(authToken)) {
            List<Book> books = bookInterface.findAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean isValidToken(String authToken) {
        return true;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookInterface.saveBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    

}
