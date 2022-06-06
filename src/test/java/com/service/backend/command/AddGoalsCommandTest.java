package com.service.backend.command;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.AddGoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.AddGoalsResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static com.service.backend.enums.StatusEnum.INTERNAL_ERROR;
import static com.service.backend.enums.StatusEnum.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
public class AddGoalsCommandTest {

    @Autowired
    private FitnessCommand<AddGoalsReqDTO, AddGoalsResDTO> underTest;

    @Autowired
    private FitnessMapper mapper;

    @MockBean
    private AddGoalsLogic logic;

    @Test
    void itShouldSuccessfullyExecuteCommandForAddGoals() {
        // Given
        final var request = new FitnessRequestEntity<AddGoalsReqDTO>();

        // Mock
        final var responseMock = new AddGoalsResDTO();
        responseMock.setStepsGoal(10000);
        responseMock.setWeightGoal(100.00);
        doReturn(responseMock).when(logic).addGoals(any());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(SUCCESS.getCode());
    }

    @Test
    void itShouldFailWithAFitnessError() {
        // Given
        final var request = new FitnessRequestEntity<AddGoalsReqDTO>();

        // Mock
        doThrow(new FitnessErrorException(
                DATABASE_ERROR.getCode(),
                DATABASE_ERROR.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR)).when(logic).addGoals(any());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(DATABASE_ERROR.getCode());
    }

    @Test
    void itShouldFailWithException() {
        // Given
        final var request = new FitnessRequestEntity<AddGoalsReqDTO>();

        // Mock
        doThrow(new RuntimeException()).when(logic).addGoals(any());

        // When
        final var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(INTERNAL_ERROR.getCode());
    }
}
