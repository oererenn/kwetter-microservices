package com.kwetter.kweetservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.kweetservice.entity.Kweet;
import com.kwetter.kweetservice.request.KweetCreationRequest;
import com.kwetter.kweetservice.service.KweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.BDDMockito.given ;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = KweetController.class)
@ActiveProfiles("test")
class KweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtDecoder jwtDecoder;

    @MockBean
    private KweetService kweetService;

    @Test
    void createKweet() throws Exception {
        final var kweetRequest = new KweetCreationRequest("oereren", "test","test");
        final var objectMapper = new ObjectMapper();
        this.mockMvc.perform(post("/api/v1/kweets").with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kweetRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void getKweet() throws Exception {
        final var kweet = Kweet.builder().id(UUID.randomUUID().toString()).build();
        given(kweetService.getKweet(kweet.getId())).willReturn(kweet);

        this.mockMvc.perform(get("/api/v1/kweets/{id}", kweet.getId()).with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteKweet() throws Exception {
        final var kweet = Kweet.builder().id(UUID.randomUUID().toString()).build();
        doNothing().when(kweetService).deleteKweet(kweet.getId());

        this.mockMvc.perform(delete("/api/v1/kweets/{id}", kweet.getId()).with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk());
    }
}