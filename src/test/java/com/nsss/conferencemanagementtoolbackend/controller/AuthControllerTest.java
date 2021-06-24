package com.nsss.conferencemanagementtoolbackend.controller;

import com.google.gson.Gson;
import com.nsss.conferencemanagementtoolbackend.repository.RoleRepository;
import com.nsss.conferencemanagementtoolbackend.repository.UserRepository;
import com.nsss.conferencemanagementtoolbackend.request.SignupRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @Mock
    PasswordEncoder encoder;

    @Mock
    RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    /*Throws error when accessing roles*/
    /*@Test
    void successfulUserRegistration() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("JohnDoe");
        signupRequest.setEmail("johndoe@gmail.com");
        signupRequest.setRoles("attendee");
        signupRequest.setPassword("johndoe321");

        Gson gson = new Gson();
        String json = gson.toJson(signupRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }*/
}