package com.piotrek.bookswapping.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piotrek.bookswapping.BookTestFactory;
import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.services.BookForExchangeService;
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
class BookForExchangeControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookForExchangeService bookForExchangeService;

    private MockMvc mockMvc;

    private BookForExchange bookForExchange;
    private BookForExchangeDto bookForExchangeDto;

    @BeforeAll
    void beforeEach() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        bookForExchange = BookTestFactory.createBookForExchange();
        bookForExchangeDto = BookTestFactory.createBookForExchangeDto();
    }

    @Test
    void findAll() throws Exception {
        List<BookForExchangeDto> bookForExchangeDtos = new ArrayList<>(1);
        bookForExchangeDtos.add(bookForExchangeDto);

        when(bookForExchangeService.findAll()).thenReturn(bookForExchangeDtos);

        mockMvc.perform(get("/books-for-exchange")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value(bookForExchangeDto.getTitle()))
                .andExpect(jsonPath("$[0].description").value(bookForExchangeDto.getDescription()))
                .andExpect(jsonPath("$[0].releaseYear").value(bookForExchangeDto.getReleaseYear()));
    }

    @Test
    void findById() throws Exception {
        when(bookForExchangeService.findById(anyLong())).thenReturn(bookForExchangeDto);

        mockMvc.perform(get("/books-for-exchange/{id}", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value(bookForExchangeDto.getTitle()))
                .andExpect(jsonPath("description").value(bookForExchangeDto.getDescription()))
                .andExpect(jsonPath("releaseYear").value(bookForExchangeDto.getReleaseYear()));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/books-for-exchange/{id}", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(bookForExchange)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Book has been updated successfully"));
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(delete("/books-for-exchange/{id}", "1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isNoContent());

    }
}