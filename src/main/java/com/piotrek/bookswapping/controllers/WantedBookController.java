package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.services.WantedBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wanted-books")
public class WantedBookController {

    private WantedBookService wantedBookService;

    public WantedBookController(WantedBookService wantedBookService) {
        this.wantedBookService = wantedBookService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<WantedBookDto> findAll() {
        return wantedBookService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public WantedBookDto findById(@PathVariable Long id) {
        return wantedBookService.findById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody WantedBook updateForWantedBook, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        wantedBookService.update(id, updateForWantedBook);
        return ResponseEntity.ok("Book has been updated successfully");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        wantedBookService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity userNotFoundHandler(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
