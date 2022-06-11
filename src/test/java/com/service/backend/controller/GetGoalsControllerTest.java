package com.service.backend.controller;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.mapper.FitnessMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.service.backend.enums.StatusEnum.SUCCESS;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {FitnessTrackingApplication.class}, properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
public class GetGoalsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldMakeRequestSuccessfully() throws Exception {
        // Given
        // When
        // Then
        mockMvc.perform(get("/goals/17517356-d88d-4870-a23e-29d9ca932247")
                        .header("Content-Type", "application/json")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", Matchers.is(SUCCESS.getCode())))
                .andExpect(jsonPath("$.status.message", is(SUCCESS.getMessage())));
    }
}
