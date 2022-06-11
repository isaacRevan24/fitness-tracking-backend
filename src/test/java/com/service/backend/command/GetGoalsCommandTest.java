package com.service.backend.command;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.GetGoalsLogic;
import com.service.backend.model.GoalsResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static com.service.backend.enums.StatusEnum.INTERNAL_ERROR;
import static com.service.backend.enums.StatusEnum.NOT_MATCHING_RECORD;
import static com.service.backend.enums.StatusEnum.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
public class GetGoalsCommandTest {

    @Autowired
    @Qualifier("GetGoalsCommand")
    private FitnessCommand<String, GoalsResDTO> underTest;

    @MockBean
    private GetGoalsLogic logic;

    @Test
    void itShouldExecuteSuccessfully() {
        // Given
        final var request = new FitnessRequestEntity<String>();

        // Mock
        final var responseMock = new GoalsResDTO();
        responseMock.setStepsGoal(10000);
        responseMock.setWeightGoal(100.00);
        doReturn(responseMock).when(logic).getGoals(any());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(SUCCESS.getCode());
    }

    @Test
    void itShouldFailBecauseNoMatchingRecord() {
        // Given
        final var request = new FitnessRequestEntity<String>();

        // Mock
        doReturn(null).when(logic).getGoals(any());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(NOT_MATCHING_RECORD.getCode());
    }

    @Test
    void itShouldFailWithAFitnessError() {
        // Given
        final var request = new FitnessRequestEntity<String>();

        // Mock
        doThrow(new FitnessErrorException(
                DATABASE_ERROR.getCode(),
                DATABASE_ERROR.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR)).when(logic).getGoals(any());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(DATABASE_ERROR.getCode());
    }

    @Test
    void itShouldFailWithException() {
        // Given
        final var request = new FitnessRequestEntity<String>();

        // Mock
        doThrow(new RuntimeException()).when(logic).getGoals(any());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(INTERNAL_ERROR.getCode());
    }
}
