package com.service.backend.controller;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.GoalsResDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.service.backend.enums.StatusEnum.SUCCESS;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
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

    @MockBean
    @Qualifier("GetGoalsCommand")
    private FitnessCommand<String, GoalsResDTO> getGoalsCommand;

    @Test
    void itShouldMakeRequestSuccessfully() throws Exception {
        // Given
        final var clientId = "5c6696ae-5f94-45d8-ab87-0754ba09e0e8";

        // Mock
        final var responseMock = new FitnessResponseEntity<GoalsResDTO>();
        responseMock.setStatus(mapper.toStatusDTO(SUCCESS));
        responseMock.setBody(new GoalsResDTO(100.00, 12000));
        doReturn(responseMock).when(getGoalsCommand).execute(any());

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
