package org.example.controller;

import org.example.model.Content;
import org.example.model.User;
import org.example.repository.ContentRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.example.Data.contentList;
import static org.example.Data.getRandomType;
import static org.example.container.EnablePostgresTestContainerContextCustomizerFactory.EnabledPostgresTestContainer;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnabledPostgresTestContainer
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
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
        Cookie cookie = new Cookie("lets-pick", UUID.randomUUID().toString());
        mockMvc.perform(post("/login")
                        .cookie(cookie))
                .andExpect(status().isForbidden())
                .andExpect(content().json(String.format("{'message':'User with this cookies %s not registered'}"
                        , cookie.getValue())))
                .andDo(print());

        Optional<User> user = userRepository.findByCookie(cookie.getValue());
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
