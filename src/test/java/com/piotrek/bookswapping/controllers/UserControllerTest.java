package com.piotrek.bookswapping.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piotrek.bookswapping.BookTestFactory;
import com.piotrek.bookswapping.UserTestFactory;
import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.dto.UserDto;
import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    private UserDto userDto;
    private User user;
    private WantedBookDto wantedBookDto;
    private WantedBook wantedBook;
    private BookForExchangeDto bookForExchangeDto;
    private BookForExchange bookForExchange;

    @BeforeAll
    void beforeEach() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        this.userDto = UserTestFactory.createDtoUser();
        this.user = UserTestFactory.createUser();

        this.wantedBookDto = BookTestFactory.createWantedBookDto();
        this.wantedBook = BookTestFactory.createWantedBook();

        this.bookForExchangeDto = BookTestFactory.createBookForExchangeDto();
        this.bookForExchange = BookTestFactory.createBookForExchange();
    }

    @Test
    void findAllUsers() throws Exception {

        List<UserDto> usersList = new ArrayList<>(1);
        usersList.add(userDto);

        when(userService.findAll()).thenReturn(usersList);

        mockMvc.perform(get("/users")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$[0].username").value(userDto.getUsername()))
                .andExpect(jsonPath("$[0].email").value(userDto.getEmail()));
    }

    @Test
    void findUser() throws Exception {
        when(userService.findById(anyLong())).thenReturn(userDto);

        mockMvc.perform(get("/users/{id}", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("username").value(userDto.getUsername()))
                .andExpect(jsonPath("email").value(userDto.getEmail()));
    }

    @Test
    void findUsersWantedBooks() throws Exception {
        List<WantedBookDto> wantedBookDtos = new ArrayList<>(1);
        wantedBookDtos.add(wantedBookDto);

        when(userService.findWantedBooksById(anyLong())).thenReturn(wantedBookDtos);

        mockMvc.perform(get("/users/{id}/wanted-books", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value(wantedBookDto.getTitle()))
                .andExpect(jsonPath("$[0].description").value(wantedBookDto.getDescription()))
                .andExpect(jsonPath("$[0].releaseYear").value(wantedBookDto.getReleaseYear()));
    }

    @Test
    void findUsersBooksForExchange() throws Exception {
        List<BookForExchangeDto> bookForExchangeDtos = new ArrayList<>(1);
        bookForExchangeDtos.add(bookForExchangeDto);

        when(userService.findBooksForExchange(anyLong())).thenReturn(bookForExchangeDtos);

        mockMvc.perform(get("/users/{id}/books-for-exchange", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value(wantedBookDto.getTitle()))
                .andExpect(jsonPath("$[0].description").value(wantedBookDto.getDescription()))
                .andExpect(jsonPath("$[0].releaseYear").value(wantedBookDto.getReleaseYear()));
    }

    @Test
    void createUser() throws Exception {
        when(userService.create(any())).thenReturn(userDto);

        mockMvc.perform(post("/users")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("username").value(userDto.getUsername()))
                .andExpect(jsonPath("email").value(userDto.getEmail()));
    }

    @Test
    void createWantedBook() throws Exception {
        when(userService.createWantedBook(anyLong(), any())).thenReturn(wantedBookDto);

        mockMvc.perform(post("/users/{id}/wanted-books", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(wantedBook)))
                .andDo(print())
                .andExpect(jsonPath("title").value(wantedBookDto.getTitle()))
                .andExpect(jsonPath("description").value(wantedBookDto.getDescription()))
                .andExpect(jsonPath("releaseYear").value(wantedBookDto.getReleaseYear()));
    }

    @Test
    void createBookForExchange() throws Exception {
        when(userService.createBookForExchange(anyLong(), any())).thenReturn(bookForExchangeDto);

        mockMvc.perform(post("/users/{id}/books-for-exchange", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bookForExchange)))
                .andDo(print())
                .andExpect(jsonPath("title").value(wantedBookDto.getTitle()))
                .andExpect(jsonPath("description").value(wantedBookDto.getDescription()))
                .andExpect(jsonPath("releaseYear").value(wantedBookDto.getReleaseYear()));
    }

    @Test
    void updateUser() throws Exception {

        mockMvc.perform(put("/users/{id}", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("User has been updated successfully"));
    }


    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/{id}", "1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}