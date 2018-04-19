//package com.piotrek.bookswapping.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.piotrek.bookswapping.config.SecurityConfig;
//import com.piotrek.bookswapping.entities.User;
//import com.piotrek.bookswapping.services.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest({UserController.class, SecurityConfig.class})
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private UserService userService;
//
//    private User userForSave;
//    private User readUser;
//
//    private final Long ID = 1L;
//    private final String EMAIL = "userForSave@email.com";
//    private final String USERNAME = "username";
//    private final String PASSWORD = "password123";
//    private final String FIRST_NAME = "Jan";
//    private final String LAST_NAME = "Kowalski";
//
//
//    @Before
//    public void setUp() {
//        userForSave = new User(FIRST_NAME, LAST_NAME,USERNAME, PASSWORD, EMAIL);
//        readUser = new User(FIRST_NAME, LAST_NAME,USERNAME, PASSWORD, EMAIL);
//        readUser.setId(1L);
//    }
//
//    @Test
//    public void postUser_ShouldReturnUser() throws Exception {
//        when(userService.create(userForSave)).thenReturn(readUser);
//
//        mockMvc.perform(post("/users")
//        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .content(objectMapper.writeValueAsString(userForSave)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("id").value(1))
//                .andExpect(jsonPath("firstName").value(FIRST_NAME))
//                .andExpect(jsonPath("lastName").value(LAST_NAME))
//                .andExpect(jsonPath("password").value(PASSWORD))
//                .andExpect(jsonPath("email").value(EMAIL))
//                .andExpect(jsonPath("username").value(USERNAME));
//    }
//
//    @Test
//    public void postUserWithoutPassword_ShouldReturnBadRequest() throws Exception {
//
//        userForSave.setPassword("");
//
//        when(userService.create(userForSave)).thenReturn(userForSave);
//
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(objectMapper.writeValueAsString(userForSave)))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void getAllUsers_ShouldReturnUsers() throws Exception {
//
//        ArrayList<User> users = new ArrayList<>();
//        users.add(readUser);
//
//        when(userService.findAll()).thenReturn(users);
//
//        mockMvc.perform(get("/users")
//        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .content(objectMapper.writeValueAsString(users)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].id").value("1"))
//                .andExpect(jsonPath("$[0].firstName").value(FIRST_NAME))
//                .andExpect(jsonPath("$[0].lastName").value(LAST_NAME))
//                .andExpect(jsonPath("$[0].password").value(PASSWORD))
//                .andExpect(jsonPath("$[0].email").value(EMAIL))
//                .andExpect(jsonPath("$[0].username").value(USERNAME));
//    }
//}