package com.piotrek.bookswapping;

import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookSwappingApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() throws Exception {
        Optional<User> user = userRepository.findById(1L);
        assertNotNull(user);
    }
}