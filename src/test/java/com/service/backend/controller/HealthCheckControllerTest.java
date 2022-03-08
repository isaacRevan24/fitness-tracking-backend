package com.service.backend.controller;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.command.FitnessCommand;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.model.StatusDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {FitnessTrackingApplication.class})
@AutoConfigureMockMvc
public class HealthCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("SignUpCommand")
    private FitnessCommand<SignUpReqDTO, StatusDTO> signUpCommand;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldHealthCheckSuccessfully() throws Exception {
        // Given
        // When
        mockMvc.perform(get("/user/health-check")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", Matchers.is(StatusEnum.SUCCESS.getCode())))
                .andExpect(jsonPath("$.status.message", is(StatusEnum.SUCCESS.getMessage())));

    }
}
