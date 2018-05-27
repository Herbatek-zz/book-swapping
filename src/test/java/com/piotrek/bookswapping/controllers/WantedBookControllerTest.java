package com.piotrek.bookswapping.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piotrek.bookswapping.BookTestFactory;
import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.services.WantedBookService;
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
class WantedBookControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WantedBookService wantedBookService;

    private MockMvc mockMvc;

    private WantedBook wantedBook;
    private WantedBookDto wantedBookDto;

    private final String TITLE = "Pan lodowego Ogrodu";
    private final String DESCRIPTION = "fajna książką";
    private final Integer RELEASE_YEAR = 2012;

    @BeforeAll
    void beforeEach() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        this.wantedBook = BookTestFactory.createWantedBook();
        this.wantedBookDto = BookTestFactory.createWantedBookDto();
    }

    @Test
    void findAll() throws Exception {
        List<WantedBookDto> wantedBookDtos = new ArrayList<>(1);
        wantedBookDtos.add(wantedBookDto);

        when(wantedBookService.findAll()).thenReturn(wantedBookDtos);

        mockMvc.perform(get("/wanted-books")
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
    void findById() throws Exception {
        when(wantedBookService.findById(anyLong())).thenReturn(wantedBookDto);

        mockMvc.perform(get("/wanted-books/{id}", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").value(wantedBookDto.getTitle()))
                .andExpect(jsonPath("description").value(wantedBookDto.getDescription()))
                .andExpect(jsonPath("releaseYear").value(wantedBookDto.getReleaseYear()));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/wanted-books/{id}", "1")
                .with(csrf())
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(wantedBook)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Book has been updated successfully"));
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(delete("/wanted-books/{id}", "1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}