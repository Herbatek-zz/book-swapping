package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Book> getBooks() {

        return null;
    }

    @PostMapping
    public ResponseEntity<Book> createBook() {

        return null;
    }

    @PutMapping
    public ResponseEntity<Book> updateBook() {

        return null;
    }

    @DeleteMapping
    public ResponseEntity<Book> deleteBook() {

        return null;
    }

}
