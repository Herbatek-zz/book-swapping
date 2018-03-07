package com.piotrek.bookswapping.controllers;

import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<User> getStudents() {

        return null;
    }

    @PostMapping
    public ResponseEntity<User> createStudent() {

        return null;
    }

    @PutMapping
    public ResponseEntity<User> updateStudent() {

        return null;
    }

    @DeleteMapping
    public ResponseEntity<User> deleteStudent() {

        return null;
    }
}
