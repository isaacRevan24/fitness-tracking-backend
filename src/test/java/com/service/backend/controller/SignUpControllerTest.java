package com.service.backend.controller;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessResponseEntity;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {FitnessTrackingApplication.class}, properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("SignUpCommand")
    private FitnessCommand<SignUpReqDTO, StatusDTO> signUpCommand;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldMakeRequestSuccessfully() throws Exception {
        // Given
        final var bodyRequest = "{ \"body\": { \"username\": \"aaaaa\", \"password\": \"aaaaa\", \"weightMetric\": \"kg\", \"weight\": 200.1, \"heightMetric\": \"mt\", \"height\": 180 } }";

        // Mock
        var responseMock = new FitnessResponseEntity<StatusDTO>();
        var status = mapper.toStatusDTO(StatusEnum.SUCCESS);
        responseMock.setBody(status);
        responseMock.setStatus(status);

        when(signUpCommand.execute(any())).thenReturn(responseMock);

        // When
        // Then
        mockMvc.perform(post("/user/sign-up")
                        .header("Content-Type", "application/json")
                        .content(bodyRequest)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", Matchers.is(StatusEnum.SUCCESS.getCode())))
                .andExpect(jsonPath("$.status.message", is(StatusEnum.SUCCESS.getMessage())));

    }
}
