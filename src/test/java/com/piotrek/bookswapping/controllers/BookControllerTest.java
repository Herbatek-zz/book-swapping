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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({BookController.class, SecurityConfig.class})
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private Book bookForSave;
    private Book readBook;

    private final String TITLE = "The Lord of the Ice Garden";
    private final String DESCTIPTION = "great bookForSave";
    private final int RELEASE_YEAR = 2005;
    private final long ID = 1L;


    @Before
    public void setUp() {

        bookForSave = new Book(TITLE);
        bookForSave.setDescription(DESCTIPTION);
        bookForSave.setReleaseYear(RELEASE_YEAR);

        readBook = new Book(TITLE);
        readBook.setDescription(DESCTIPTION);
        readBook.setReleaseYear(RELEASE_YEAR);
        readBook.setId(ID);
    }

    @Test
    public void getAllBooks_returnAllBooks() throws Exception {

        ArrayList<Book> books = new ArrayList<>();
        books.add(readBook);

        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(books)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value(TITLE))
                .andExpect(jsonPath("$[0].description").value(DESCTIPTION))
                .andExpect(jsonPath("$[0].releaseYear").value(RELEASE_YEAR));
    }

    @Test
    public void getBookById_returnBook() throws Exception {

        when(bookService.findBookById(anyLong())).thenReturn(readBook);

        mockMvc.perform(get("/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(readBook)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("title").value(TITLE))
                .andExpect(jsonPath("description").value(DESCTIPTION))
                .andExpect(jsonPath("releaseYear").value(RELEASE_YEAR));
    }

    @Test
    public void updateBook_returnUpdatedBook() throws Exception{
        throw new Exception("napisz ten test");
    }

    @Test
    public void deleteBook_returnNO_CONTENT() throws Exception {
        mockMvc.perform(delete("/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }

}