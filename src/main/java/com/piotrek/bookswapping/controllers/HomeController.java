package com.piotrek.bookswapping.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    @GetMapping("/")
    public String homePage(HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof UserDetails)
            username = ((UserDetails)principal).getUsername();

        else
            username = principal.toString();

        return "Witaj na stronie głównej " + username + " widzę, że korzystasz z " + request.getHeader("User-Agent");
    }

}
