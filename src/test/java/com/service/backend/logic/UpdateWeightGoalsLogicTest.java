package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.repository.GoalsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

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
        final var weightGoal

        // When

        // Then
    }
}
