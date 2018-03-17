package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> findAllBooks() {
        Iterable<Book> books = bookService.findAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> findBook(@PathVariable Long bookId) {
        Book book = bookService.findBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<?> createBook(@Valid @RequestBody Book book,
//                                        BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            List errors = bindingResult.getAllErrors();
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//        Book createdBook = bookService.createBook(book);
//        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
//    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @Valid @RequestBody Book updateForBook) {
        Book updatedBook = bookService.updateBook(bookId, updateForBook);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<String> bookNotFound(BookNotFoundException e) {
        return new ResponseEntity<>(e.getMESSAGE(), HttpStatus.NOT_FOUND);
    }

}
