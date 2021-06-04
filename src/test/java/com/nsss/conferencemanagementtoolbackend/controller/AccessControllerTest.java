package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AccessControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccessController accessController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accessController).build();
    }

    @Test
    void allHomeAccessSuccessful() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/access/all/home")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void allRPAccessSuccessful() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/access/all/rp")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void allWPAccessSuccessful() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/access/all/wp")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void allDownloadAccessSuccessful() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/access/all/download")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void allContactUsAccessSuccessful() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/access/all/contactus")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}