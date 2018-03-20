package com.piotrek.bookswapping.controllers;


import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.services.BookForExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("books-for-exchange")
public class BookForExchangeController {

    private BookForExchangeService bookForExchangeService;

    public BookForExchangeController(BookForExchangeService bookForExchangeService) {
        this.bookForExchangeService = bookForExchangeService;
    }


    @GetMapping
    public ResponseEntity<Iterable<BookForExchange>> findAll() {
        Iterable<BookForExchange> books = bookForExchangeService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookForExchange> findById(@PathVariable Long id) {
        BookForExchange book = bookForExchangeService.findById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody BookForExchange updateForBook, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        BookForExchange book = bookForExchangeService.update(id, updateForBook);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        bookForExchangeService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> userNotFoundHandler(BookNotFoundException e) {
        return new ResponseEntity<>(e.getMESSAGE(), HttpStatus.NOT_FOUND);
    }
}
