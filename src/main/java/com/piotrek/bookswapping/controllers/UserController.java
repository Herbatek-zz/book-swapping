package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.exceptions.UserNotFoundException;
import com.piotrek.bookswapping.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> findAllUsers() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User updateForUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        User updatedUser = userService.update(id, updateForUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/wanted-books")
    public ResponseEntity<?> createWantedBook(@PathVariable Long id, @Valid @RequestBody WantedBook wantedBook, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        WantedBook createdBook = userService.createWantedBook(id, wantedBook);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PostMapping("{id}/books-for-exchange")
    public ResponseEntity<?> createBookForExchange(@PathVariable Long id, @Valid @RequestBody BookForExchange bookForExchange, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        BookForExchange createdBook = userService.createBookForExchange(id, bookForExchange);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> userNotFoundHandler(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMESSAGE(), HttpStatus.NOT_FOUND);
    }
}
