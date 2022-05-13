package com.service.backend.controller;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignInReqDTO;
import com.service.backend.model.SignInResDTO;
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
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {FitnessTrackingApplication.class}, properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
class SignInControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FitnessMapper mapper;

    @MockBean
    @Qualifier("SignInCommand")
    private FitnessCommand<SignInReqDTO, SignInResDTO> signInCommand;

    @Test
    void itShouldMakeRequestSuccessfully() throws Exception {
        // Given
        final var bodyRequest = "{ \"body\": { \"username\": \"aizack\", \"password\": \"password123\" } }";

        // Mock
        final var mockResponse = new FitnessResponseEntity<SignInResDTO>();
        mockResponse.setStatus(mapper.toStatusDTO(StatusEnum.SUCCESS));
        mockResponse.setBody(new SignInResDTO("c08cb8f4-ca26-4635-8f13-0270ef8ea371"));
        doReturn(mockResponse).when(signInCommand).execute(any());

        // When
        // Then
        mockMvc.perform(post("/user/sign-in")
                        .header("Content-Type", "application/json")
                        .content(bodyRequest)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", Matchers.is(StatusEnum.SUCCESS.getCode())))
                .andExpect(jsonPath("$.status.message", is(StatusEnum.SUCCESS.getMessage())));

    }
}
