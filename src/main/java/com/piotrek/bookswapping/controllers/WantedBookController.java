package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.services.WantedBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wanted-books")
public class WantedBookController {

    private WantedBookService wantedBookService;

    public WantedBookController(WantedBookService wantedBookService) {
        this.wantedBookService = wantedBookService;
    }


    @GetMapping
    public ResponseEntity<Iterable<WantedBook>> findAllWantedBooks() {
        Iterable<WantedBook> wantedBooks = wantedBookService.findAll();
        return new ResponseEntity<>(wantedBooks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WantedBook> findWantedBookById(@PathVariable Long id) {
        WantedBook wantedBook = wantedBookService.findById(id);
        return new ResponseEntity<>(wantedBook, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateWantedBook(@PathVariable Long id, @RequestBody WantedBook updateForWantedBook, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        WantedBook wantedBook = wantedBookService.update(id, updateForWantedBook);
        return new ResponseEntity<>(wantedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWantedBook(@PathVariable Long id) {
        wantedBookService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
