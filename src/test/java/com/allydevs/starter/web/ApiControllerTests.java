package com.allydevs.starter.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTests {

  @Autowired private MockMvc mockMvc;

  @Test
  void returnsApplicationInfo() throws Exception {
    mockMvc
        .perform(get("/api/v1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Spring Boot Starter Kit"))
        .andExpect(jsonPath("$.version").value("0.1.0"))
        .andExpect(jsonPath("$.status").value("UP"));
  }

  @Test
  void echoesValidRequest() throws Exception {
    mockMvc
        .perform(
            post("/api/v1/echo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                                {
                                  "message": "Hello AllyDevs"
                                }
                                """))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Hello AllyDevs"));
  }

  @Test
  void rejectsInvalidRequest() throws Exception {
    mockMvc
        .perform(
            post("/api/v1/echo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                                {
                                  "message": ""
                                }
                                """))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.title").value("Invalid request"))
        .andExpect(jsonPath("$.detail").value("Request validation failed"))
        .andExpect(jsonPath("$.errors.message").value("message must not be blank"));
  }
}
