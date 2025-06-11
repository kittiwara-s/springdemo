package com.example.springdemo.controller;

import com.example.springdemo.model.UserModel;
import com.example.springdemo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository repository;


    @BeforeEach
    void setUp() {
        repository.deleteAll();
        UserModel user = UserModel.builder()
                .name("Kittiwara")
                .email("kittiwara.sornla@gmail.com")
                .password("P@ssw0rd!!")
                .build();
        repository.save(user);
    }

    @Test
    void createUser() throws Exception {
        String req = """
                { "name":"Kittiwara","email":"kittiwara.sornla@gmail.com","password":"P@ssw0rd!!"}
                """;
        mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Kittiwara"))
                .andExpect(jsonPath("$.email").value("kittiwara.sornla@gmail.com"))
                .andExpect(jsonPath("$.password").value("P@ssw0rd!!"));
    }

    @Test
    void login() throws Exception {
        String req = """
                { "email":"kittiwara.sornla@gmail.com","password":"P@ssw0rd!!"}
                """;
        mockMvc.perform(get("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Kittiwara"))
                .andExpect(jsonPath("$.email").value("kittiwara.sornla@gmail.com"))
                .andExpect(jsonPath("$.password").value("P@ssw0rd!!"));
    }

    @Test
    void updateUser() throws Exception {
        String req = """
                { "name":"Kittiwara","email":"kittiwara.sornla@gmail.com"}
                """;

        Long id = 1L;
        mockMvc.perform(put("/user/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Kittiwara"))
                .andExpect(jsonPath("$.email").value("kittiwara.sornla@gmail.com"))
                .andExpect(jsonPath("$.password").value("P@ssw0rd!!"));
    }
}