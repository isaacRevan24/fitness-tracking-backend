package com.service.backend.command;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.GoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.UpdateStepsGoalReqDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static com.service.backend.enums.StatusEnum.INTERNAL_ERROR;
import static com.service.backend.enums.StatusEnum.INVALID_STEP_GOAL;
import static com.service.backend.enums.StatusEnum.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
class UpdateStepsGoalCommandTest {

    @Autowired
    private FitnessMapper mapper;

    @MockBean
    private GoalsLogic logic;

    @Autowired
    @Qualifier("UpdateStepsGoalCommand")
    private FitnessCommand<UpdateStepsGoalReqDTO, Void> underTest;

    @Test
    void itShouldSuccessfullyUpdateStepGoal() {
        // Given
        final var request = new FitnessRequestEntity<UpdateStepsGoalReqDTO>();
        request.setBody(new UpdateStepsGoalReqDTO(UUID.fromString("e47dc1b6-5dda-46fa-aeaa-9a1c38564477"), 100));

        // Mock
        final var mockStatus = mapper.toStatusDTO(StatusEnum.SUCCESS);
        doReturn(mockStatus).when(logic).updateStepsGoal(any(), anyInt());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(SUCCESS.getCode());
    }

    @Test
    void itShouldFailBecauseInvalidStepsValues() {
        // Given
        final var request = new FitnessRequestEntity<UpdateStepsGoalReqDTO>();
        request.setBody(new UpdateStepsGoalReqDTO(UUID.fromString("e47dc1b6-5dda-46fa-aeaa-9a1c38564477"), 1000000));

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(INVALID_STEP_GOAL.getCode());
    }

    @Test
    void itShouldFailBecauseFitnessError() {
        // Given
        final var request = new FitnessRequestEntity<UpdateStepsGoalReqDTO>();
        request.setBody(new UpdateStepsGoalReqDTO(UUID.fromString("e47dc1b6-5dda-46fa-aeaa-9a1c38564477"), 100));

        // Mock
        doThrow(new FitnessErrorException(
                DATABASE_ERROR.getCode(),
                DATABASE_ERROR.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR)).when(logic).updateStepsGoal(any(), anyInt());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(DATABASE_ERROR.getCode());
    }

    @Test
    void itShouldFailBecauseInternalError() {
        // Given
        final var request = new FitnessRequestEntity<UpdateStepsGoalReqDTO>();
        request.setBody(new UpdateStepsGoalReqDTO(UUID.fromString("e47dc1b6-5dda-46fa-aeaa-9a1c38564477"), 100));

        // Mock
        doThrow(new RuntimeException()).when(logic).updateStepsGoal(any(), anyInt());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(INTERNAL_ERROR.getCode());
    }
}
