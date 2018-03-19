package com.piotrek.bookswapping.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/")
    public String homePage(Principal user) {
        return "Witaj na stronie głównej\n" + user.getName();
    }
}
