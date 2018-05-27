//package com.piotrek.bookswapping.services;
//
//import com.piotrek.bookswapping.UserTestFactory;
//import com.piotrek.bookswapping.dto.UserDto;
//import com.piotrek.bookswapping.entities.User;
//import com.piotrek.bookswapping.repositories.UserRepository;
//import com.piotrek.bookswapping.services.converters.UserConverter;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//class UserServiceTest {
//
//    @MockBean
//    private UserRepository userRepository;
//    @MockBean
//    private BookForExchangeService bookForExchangeService;
//    @MockBean
//    private WantedBookService wantedBookService;
//    @MockBean
//    private UserConverter userConverter;
//
//    private UserService userService;
//    private User user;
//    private UserDto userDto;
//
//    @BeforeEach
//    void setUp() {
//        user = UserTestFactory.createUser();
//        user.setId(1L);
//
//        userDto = UserTestFactory.createDtoUser();
//
//        userService = new UserService(userRepository, bookForExchangeService, wantedBookService, userConverter);
//    }
//
//    @Test
//    void findAll() {
//
//    }
//
//    @Test
//    void findById() {
//        when(userConverter.convertToDto(any())).thenReturn(userDto);
//        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
//
//        UserDto readUserDto = userService.findById(user.getId());
//
//        assertEquals(user.getUsername(), readUserDto.getUsername());
//        assertEquals(user.getEmail(), readUserDto.getEmail());
//        assertEquals(user.getFirstName(), readUserDto.getFirstName());
//        assertEquals(user.getLastName(), readUserDto.getLastName());
//    }
//
//    @Test
//    void create() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void createWantedBook() {
//    }
//
//    @Test
//    void createBookForExchange() {
//    }
//
//    @Test
//    void findWantedBooksById() {
//    }
//
//    @Test
//    void findBooksForExchange() {
//    }
//}