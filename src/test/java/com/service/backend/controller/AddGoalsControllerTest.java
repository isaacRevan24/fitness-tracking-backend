package com.service.backend.controller;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {FitnessTrackingApplication.class}, properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
public class AddGoalsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldMakeRequestSuccessfully() throws Exception {
        // Given
        final var bodyRequest = "{ \"body\": { \"clientId\": \"c1822942-0820-45db-955b-70f762b1e872\", \"weightGoal\": 100.00, \"stepsGoal\": 12000 } }";

        // When
        // Then
        mockMvc.perform(post("/goals")
                        .header("Content-Type", "application/json")
                        .content(bodyRequest)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", Matchers.is(StatusEnum.SUCCESS.getCode())))
                .andExpect(jsonPath("$.status.message", is(StatusEnum.SUCCESS.getMessage())));
    }
}
