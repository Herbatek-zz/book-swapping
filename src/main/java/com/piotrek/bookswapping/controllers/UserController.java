package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.entities.User;
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
        Iterable<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUser(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("{userId}/books")
    public ResponseEntity<?> createBook(@PathVariable Long userId, @Valid @RequestBody Book book,
                                        BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            List<org.springframework.validation.ObjectError> errors = bindingResult.getAllErrors();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        Book createdBook = userService.createBook(userId, book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @Valid @RequestBody User updateForUser) {
        User updatedUser = userService.updateUser(userId, updateForUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<String> userNotFoundHandler(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMESSAGE(), HttpStatus.NOT_FOUND);
    }
}
