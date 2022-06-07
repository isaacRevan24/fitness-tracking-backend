package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.repository.GoalsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
public class AddGoalsLogicTest {

    @Autowired
    private AddGoalsLogic underTest;

    @MockBean
    private GoalsRepository goalsRepository;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldAddNewGoals() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var request = new AddGoalsReqDTO(clientId, 110.00, 12000);

        // Mock
        final var goalsEntity = mapper.toGoalsEntity(request);
        doReturn(goalsEntity).when(goalsRepository).save(any());

        // When
        final var response = underTest.addGoals(request);

        // Then
        assertThat(response.getStepsGoal()).isEqualTo(request.getStepsGoal());
    }

    @Test
    void itShouldFailAddingGoalsInDataBase() {
        // Given
        final var clientId = UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8");
        final var request = new AddGoalsReqDTO(clientId, 110.00, 12000);

        // Mock
        doThrow(new RuntimeException()).when(goalsRepository).save(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.addGoals(request))
                .isInstanceOf(FitnessErrorException.class);
    }
}
