package org.example.controller;

import jakarta.servlet.http.Cookie;
import org.example.container.TestWithPostgresContainer;
import org.example.model.Content;
import org.example.model.User;
import org.example.repository.ContentRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.Data.contentList;
import static org.example.Data.getRandomType;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest extends TestWithPostgresContainer {
    @Autowired
    private MockMvc mockMvc;
    private final static MediaType mediaType = MediaType.APPLICATION_JSON;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Test
    @Order(2)
    public void givenRequestWithoutCookies_shouldSaveNewUser() throws Exception {
        mockMvc.perform(post("/login"))
                .andExpect(status().isCreated())
                .andDo(print());
        User user = userRepository.findById(1L).get();
        assertNotNull(user);
        assertEquals(user.getId(), 1L);
    }

    @Test
    @Order(1)
    public void givenRequestWithBadCookies_shouldReturnStatusForbidden_andNotSaveUser() throws Exception {
        Cookie cookie = new Cookie(UUID.randomUUID().toString(), "");
        mockMvc.perform(post("/login")
                        .cookie(cookie))
                .andExpect(status().isForbidden())
                .andExpect(content().json(String.format("{'message':'User with this cookies %s not registered'}"
                        ,cookie.getName())))
                .andDo(print());
        Optional<User> user = userRepository.findById(1L);
        assertTrue(user.isEmpty());
    }


    @Test
    @Order(3)
    public void savedUserShouldHavePipeLineOfContent() throws Exception {
        saveContent();
        mockMvc.perform(post("/login"))
                .andExpect(status().isCreated());
        User user = userRepository.findById(1L).get();
        List<Content> userContent = contentRepository.findNotWatchedContentByUserId(user.getId(), getRandomType().name());
        assertNotNull(userContent);
    }

    private void saveContent() {
        contentRepository.saveAll(contentList);
    }
}