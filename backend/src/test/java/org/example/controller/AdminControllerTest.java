package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ContentResponse;
import org.example.model.enums.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;

import static org.example.container.EnablePostgresTestContainerContextCustomizerFactory.EnabledPostgresTestContainer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@EnabledPostgresTestContainer
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenContentForSave_ShouldReturnThisContent() throws Exception {
        Cookie cookie = registerUserAndReturnCookie();

        ContentResponse contentForSave = new ContentResponse("testContent", ContentType.IMAGE);
        mockMvc.perform(post("/admin").cookie(cookie)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contentForSave)))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenVideoContentForSave_contentShouldHaveOnlyVideoIdInDatabase() throws Exception {
        Cookie cookie = registerUserAndReturnCookie();

        ContentResponse contentForSave = new ContentResponse("https://www.youtube.com/watch?v=TYArJMeEAtI&t=17s", ContentType.VIDEO);

        mockMvc.perform(post("/admin").cookie(cookie).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contentForSave)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(new ContentResponse("TYArJMeEAtI", ContentType.VIDEO)));
    }

    private Cookie registerUserAndReturnCookie() throws Exception {
        MvcResult result = mockMvc.perform(post("/login"))
                .andDo(print())
                .andReturn();
        return result.getResponse().getCookie("lets-pick");
    }
}
