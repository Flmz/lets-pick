package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ContentResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.UUID;

import static org.example.container.EnablePostgresTestContainerContextCustomizerFactory.EnabledPostgresTestContainer;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Sql(scripts = "classpath:/init.sql")
@EnabledPostgresTestContainer
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerNewUser_ThisUserStartPlay_ShouldReturnTwoContentsWithSameTypes() throws Exception {

        Cookie cookie = registerUserAndReturnCookie();

        MvcResult result = mockMvc.perform(get("/")
                        .cookie(cookie))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", hasSize(2)
                        )).andReturn();
        ContentResponse[] bodyResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ContentResponse[].class);
        assertNotNull(bodyResponse[0].getUrl());
        assertNotNull(bodyResponse[1].getUrl());
        assertEquals(bodyResponse[0].getType(), bodyResponse[1].getType());
        assertNotEquals(bodyResponse[0].getUrl(), bodyResponse[1].getUrl());
    }

    @Test
    public void ShouldAlwaysReturnDifferentContentUrlButSingleTypes() throws Exception {
        Cookie cookie = registerUserAndReturnCookie();

        for (int i = 0; i < 1000; i++) {
            MvcResult result = mockMvc.perform(get("/")
                            .cookie(cookie))
                    .andDo(print())
                    .andExpectAll(
                            status().isOk(),
                            jsonPath("$", hasSize(2)
                            )).andReturn();
            ContentResponse[] bodyResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ContentResponse[].class);
            assertNotNull(bodyResponse[0].getUrl());
            assertNotNull(bodyResponse[1].getUrl());
            assertEquals(bodyResponse[0].getType(), bodyResponse[1].getType());
            assertNotEquals(bodyResponse[0].getUrl(), bodyResponse[1].getUrl());
        }
    }

    @Test
    public void sendRequestWithUnknownCookie_shouldReturnStatusForbidden() throws Exception {
        Cookie cookie = new Cookie("test", UUID.randomUUID().toString());
        mockMvc.perform(get("/").cookie(cookie))
                .andExpect(status().isForbidden());
    }

    private Cookie registerUserAndReturnCookie() throws Exception {
        MvcResult result = mockMvc.perform(post("/login"))
                .andDo(print())
                .andReturn();
        return result.getResponse().getCookie("lets-pick");
    }
}
