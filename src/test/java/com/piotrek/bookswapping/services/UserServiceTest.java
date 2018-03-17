package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.exceptions.UserNotFoundException;
import com.piotrek.bookswapping.respositories.BookRepository;
import com.piotrek.bookswapping.respositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.constraints.Null;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookService bookService;

    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("kenshin");
        user.setEmail("kenshin9@mail.com");
        user.setFirstName("piotrek");
        user.setLastName("cuzytek");

        userService = new UserService(userRepository, bookService);
    }


    @Test
    public void findUserById_ReturnUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        User readUser = userService.findUserById(user.getId());

        assertEquals(user.getId(), readUser.getId());
        assertEquals(user.getUsername(), readUser.getUsername());
        assertEquals(user.getPassword(), readUser.getPassword());
        assertEquals(user.getEmail(), readUser.getEmail());
        assertEquals(user.getFirstName(), readUser.getFirstName());
        assertEquals(user.getLastName(), readUser.getLastName());
    }

    @Test(expected = UserNotFoundException.class)
    public void findUserById_WhenCarNotFound_ThrowException() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        userService.findUserById(user.getId());
    }

    @Test
    public void createUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }
}