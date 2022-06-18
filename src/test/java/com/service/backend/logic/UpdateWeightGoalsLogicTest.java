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

import static com.service.backend.enums.StatusEnum.ERROR_UPDATING_WEIGHT;
import static com.service.backend.enums.StatusEnum.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
class UpdateWeightGoalsLogicTest {

    @Autowired
    private GoalsLogic underTest;

    @MockBean
    private GoalsRepository goalsRepository;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldUpdateWeightGoal() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var weightGoal = 96.5;

        // Mock
        doReturn(1).when(goalsRepository).updateWeighGoal(any(), anyDouble());

        // When
        final var response = underTest.updateWeightGoal(clientId, weightGoal);

        // Then
        assertThat(response.getCode()).isEqualTo(SUCCESS.getCode());
    }

    @Test
    void itShouldFailBecauseNoRecordUpdated() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var weightGoal = 96.5;

        // Mock
        doReturn(0).when(goalsRepository).updateWeighGoal(any(), anyDouble());

        // When
        final var response = underTest.updateWeightGoal(clientId, weightGoal);

        // Then
        assertThat(response.getCode()).isEqualTo(ERROR_UPDATING_WEIGHT.getCode());
    }

    @Test
    void itShouldFailBecauseDatabaseError() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var weightGoal = 96.5;

        // Mock
        doThrow(new RuntimeException()).when(goalsRepository).updateWeighGoal(any(), anyDouble());

        // When
        // Then
        assertThatThrownBy(() -> underTest.updateWeightGoal(clientId, weightGoal))
                .isInstanceOf(FitnessErrorException.class);
    }
}
