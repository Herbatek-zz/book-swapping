package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.dto.UserDto;
import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.exceptions.UserNotFoundException;
import com.piotrek.bookswapping.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // get

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<UserDto> findAllUsers() {
        log.info("Getting all users");
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public UserDto findUser(@PathVariable Long userId) {
        log.info("Getting user with id {}", userId);
        return userService.findById(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/wanted-books")
    public Iterable<WantedBookDto> findUsersWantedBooks(@PathVariable Long userId) {
        log.info("Getting all wanted-books for user with id {}", userId);
        return userService.findWantedBooksById(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/books-for-exchange")
    public Iterable<BookForExchangeDto> findUsersBooksForExchange(@PathVariable Long userId) {
        log.info("Getting all books-for-exchange for user with id {}", userId);
        return userService.findBooksForExchange(userId);
    }

    // post

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("While creating user some errors occurred");
            return ResponseEntity.badRequest().build();
        }
        UserDto createdUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("{id}/wanted-books")
    public ResponseEntity<?> createWantedBook(@PathVariable Long id, @Valid @RequestBody WantedBook wantedBook, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        WantedBookDto createdBook = userService.createWantedBook(id, wantedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PostMapping("{id}/books-for-exchange")
    public ResponseEntity<?> createBookForExchange(@PathVariable Long id, @Valid @RequestBody BookForExchange bookForExchange, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        BookForExchangeDto createdBook = userService.createBookForExchange(id, bookForExchange);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    // put

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User updateForUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        userService.update(id, updateForUser);
        return ResponseEntity.ok().body("User has been updated successfully");
    }

    // delete

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity userNotFoundHandler(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
