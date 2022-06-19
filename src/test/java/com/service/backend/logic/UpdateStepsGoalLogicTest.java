package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.repository.GoalsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static com.service.backend.enums.StatusEnum.ERROR_UPDATING_STEPS;
import static com.service.backend.enums.StatusEnum.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
class UpdateStepsGoalLogicTest {

    @Autowired
    private GoalsLogic underTest;

    @MockBean
    private GoalsRepository goalsRepository;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldSuccessfullyUpdateStepsGoal() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var steps = 96;

        // Mock
        doReturn(1).when(goalsRepository).updateStepsGoal(any(), anyInt());

        // When
        final var response = underTest.updateStepsGoal(clientId, steps);

        // Then
        assertThat(response.getCode()).isEqualTo(SUCCESS.getCode());
    }

    @Test
    void itShouldFailBecauseNoRecordUpdated() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var steps = 96;

        // Mock
        doReturn(0).when(goalsRepository).updateStepsGoal(any(), anyInt());

        // When
        final var response = underTest.updateStepsGoal(clientId, steps);

        // Then
        assertThat(response.getCode()).isEqualTo(ERROR_UPDATING_STEPS.getCode());
    }

    @Test
    void itShouldFailBecauseDatabaseError() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var steps = 96;

        // Mock
        doThrow(new RuntimeException()).when(goalsRepository).updateStepsGoal(any(), anyInt());

        // When
        // Then
        assertThatThrownBy(() -> underTest.updateStepsGoal(clientId, steps))
                .isInstanceOf(FitnessErrorException.class);
    }
}
