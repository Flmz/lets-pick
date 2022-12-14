package org.example.controller;

import org.example.container.TestWithPostgresContainer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest extends TestWithPostgresContainer {

}
