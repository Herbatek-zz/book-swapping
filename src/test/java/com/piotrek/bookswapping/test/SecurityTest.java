package com.piotrek.bookswapping.test;

import com.piotrek.bookswapping.controllers.HomeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WithUserDetails("kenshin")
class SecurityTest {

    @Autowired
    private HomeController homeController;

    @Test
    @WithAnonymousUser
    void homeControllerTestException() {
        assertThrows(AccessDeniedException.class, () -> homeController.homePage());
    }

    @Test
    @WithMockUser
    void homeControllerTest() {
        homeController.homePage();
    }
}
