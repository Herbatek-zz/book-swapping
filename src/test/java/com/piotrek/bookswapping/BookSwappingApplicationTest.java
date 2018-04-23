package com.piotrek.bookswapping;

import com.piotrek.bookswapping.controllers.BookForExchangeController;
import com.piotrek.bookswapping.controllers.HomeController;
import com.piotrek.bookswapping.controllers.UserController;
import com.piotrek.bookswapping.controllers.WantedBookController;
import com.piotrek.bookswapping.repositories.BookForExchangeRepository;
import com.piotrek.bookswapping.repositories.RoleRepository;
import com.piotrek.bookswapping.repositories.UserRepository;
import com.piotrek.bookswapping.repositories.WantedBookRepository;
import com.piotrek.bookswapping.services.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookSwappingApplicationTest {

    @Autowired private BookForExchangeRepository bookForExchangeRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private WantedBookRepository wantedBookRepository;

    @Autowired private BookForExchangeService bookForExchangeService;
    @Autowired private RoleService roleService;
    @Autowired private UserDetailsServiceImpl userDetailsServiceImplService;
    @Autowired private UserService userService;
    @Autowired private WantedBookService wantedBookService;

    @Autowired private BookForExchangeController bookForExchangeController;
    @Autowired private HomeController homeController;
    @Autowired private UserController userController;
    @Autowired private WantedBookController wantedBookController;


    @Test
    void repositoriesLoad() {
        assertAll(
                () -> assertNotNull(bookForExchangeRepository),
                () -> assertNotNull(roleRepository),
                () -> assertNotNull(wantedBookRepository),
                () -> assertNotNull(userRepository)
        );
    }

    @Test
    void servicesLoad() {
        assertAll(
                () -> assertNotNull(bookForExchangeService),
                () -> assertNotNull(roleService),
                () -> assertNotNull(userDetailsServiceImplService),
                () -> assertNotNull(userService),
                () -> assertNotNull(wantedBookService)
        );
    }

    @Test
    void controllersLoad() {
        assertAll(
                () -> assertNotNull(bookForExchangeController),
                () -> assertNotNull(homeController),
                () -> assertNotNull(userController),
                () -> assertNotNull(wantedBookController)
        );
    }

}