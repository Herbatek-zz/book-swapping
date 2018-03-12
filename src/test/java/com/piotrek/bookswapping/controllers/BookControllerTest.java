package com.piotrek.bookswapping.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piotrek.bookswapping.config.SecurityConfig;
import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.services.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({BookController.class, SecurityConfig.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book book;

    @Before
    public void before() {
        book = new Book();
        book.setId(1L);
        book.setTitle("The Lord of the Ice Garden");
        book.setDescription("great book");
        book.setReleaseYear(2005);
    }

    @Test
    public void givenBookWhenCreateBookThenReturnCreatedBook() throws Exception {

        when(bookService.createBook(book)).thenReturn(book);

        mvc.perform(post("/books", book)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated());

    }

}