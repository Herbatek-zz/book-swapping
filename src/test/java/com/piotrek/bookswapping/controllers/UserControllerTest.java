package com.piotrek.bookswapping.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piotrek.bookswapping.config.SecurityConfig;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.services.UserService;
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
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest({UserController.class, SecurityConfig.class})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private User user;

    @Before
    public void init() {
        user = new User();
        user.setId(1L);
        user.setFirstName("Piotr");
        user.setLastName("Cużytek");
        user.setPassword("password");
        user.setUsername("kenshin");
        user.setEmail("kenshin@email.com");

    }

    @Test
    public void postUser_ShouldReturnUser() throws Exception {
        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("firstName").value("Piotr"))
                .andExpect(jsonPath("lastName").value("Cużytek"))
                .andExpect(jsonPath("password").value("password"))
                .andExpect(jsonPath("email").value("kenshin@email.com"))
                .andExpect(jsonPath("username").value("kenshin"));
    }

    @Test
    public void postUserWithoutPassword_ShouldReturnBadRequest() throws Exception {

        user.setPassword(null);

        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllUsers_ShouldReturnUsers() throws Exception {

        ArrayList<User> users = new ArrayList<>();
        users.add(user);

        when(userService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(users)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].firstName").value("Piotr"))
                .andExpect(jsonPath("$[0].lastName").value("Cużytek"))
                .andExpect(jsonPath("$[0].password").value("password"))
                .andExpect(jsonPath("$[0].email").value("kenshin@email.com"))
                .andExpect(jsonPath("$[0].username").value("kenshin"));
    }
}