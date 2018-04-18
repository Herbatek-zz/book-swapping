package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.services.BookForExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("books-for-exchange")
public class BookForExchangeController {

    private BookForExchangeService bookForExchangeService;

    public BookForExchangeController(BookForExchangeService bookForExchangeService) {
        this.bookForExchangeService = bookForExchangeService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<BookForExchangeDto> findAll() {
        log.info("Getting all books-for-exchange for exchange");
        return bookForExchangeService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookForExchangeDto findById(@PathVariable Long id) {
        log.info("Getting book-for-exchange with id {}", id);
        return bookForExchangeService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody BookForExchange updateForBook, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("While processing update for book-for-exchange with id {} some errors occurred", id);
            return ResponseEntity.badRequest().build();
        }
        log.info("Updating book-for-exchange with id {}", id);
        bookForExchangeService.update(id, updateForBook);
        return ResponseEntity.ok("Book has been updated successfully");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Deleting book-for-exchange with id {}", id);
        bookForExchangeService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity userNotFoundHandler(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
