package com.service.backend.controller;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.UpdateWeightGoalReqDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
@AutoConfigureMockMvc
class UpdateWeightGoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("UpdateWeightGoalsCommand")
    private FitnessCommand<UpdateWeightGoalReqDTO, Void> updateWeightGoalCommand;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldMakeRequestSuccessfully() throws Exception {
        // Given
        final var bodyRequest = "{ \"body\": { \"clientId\": \"b92a9fba-e671-470c-b08f-e9840b5d2900\", \"weightGoal\": 97.00 } }";

        // Mock
        final var responseMock = new FitnessResponseEntity<Void>();
        var status = mapper.toStatusDTO(SUCCESS);
        responseMock.setStatus(status);

        doReturn(responseMock).when(updateWeightGoalCommand).execute(any());

        // When
        // Then
        mockMvc.perform(put("/goals/weight")
                        .header("Content-Type", "application/json")
                        .content(bodyRequest)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status.code", Matchers.is(SUCCESS.getCode())))
                .andExpect(jsonPath("$.status.message", is(SUCCESS.getMessage())));
    }
}
