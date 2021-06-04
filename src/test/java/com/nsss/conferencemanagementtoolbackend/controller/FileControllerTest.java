package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.repository.ResearchFileRepository;
import com.nsss.conferencemanagementtoolbackend.services.ResearchFileService;
import com.nsss.conferencemanagementtoolbackend.services.WorkshopFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FileControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ResearchFileService researchFileService;

    @Mock
    private WorkshopFileService workshopFileService;

    @Mock
    private ResearchFileRepository researchFileRepository;

    @InjectMocks
    private FileController fileController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    void uploadRPFilesSuccessful() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "testRPFile.txt",
                "text/plain", "file body".getBytes());

        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/api/access/wp/upload")
                .file(file)
                .param("user", "rp1")
                .param("approvalStatus", "false")
                .param("paymentStatus", "false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Uploaded the file successfully: testRPFile.txt"));
    }

    @Test
    void getListRPFilesSuccessful() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/access/rp/files")
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void uploadWPFilesSuccessful() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "testWPFile.txt",
                "text/plain", "file body".getBytes());

        mockMvc.perform(
                MockMvcRequestBuilders.multipart("/api/access/rp/upload")
                        .file(file)
                        .param("user", "wp1")
                        .param("approvalStatus", "false")
                        .param("paymentStatus", "false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Uploaded the file successfully: testWPFile.txt"));
    }

    @Test
    void getListWPFilesSuccessful() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/access/wp/files")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}