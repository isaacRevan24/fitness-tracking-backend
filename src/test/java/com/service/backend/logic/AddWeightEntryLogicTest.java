package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.AddWeightTrackReqDTO;
import com.service.backend.repository.WeightTrackRepository;
import com.service.backend.repository.entities.WeightTrackEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
public class AddWeightEntryLogicTest {

    @Autowired
    private TrackingLogic underTest;

    @MockBean
    private WeightTrackRepository repository;

    @Test
    void itShouldSuccessfullyAddWeightEntry() {
        // Given
        final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final var now = "2016-11-09 10:30:22";
        final var formatDateTime = LocalDateTime.parse(now, formatter);
        final var request = new AddWeightTrackReqDTO();
        request.setWeight(100.0);
        request.setDate(formatDateTime);
        request.setClientId(UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8"));

        // Mock
        final var mockResponse = new WeightTrackEntity();
        mockResponse.setWeightTrackId(UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8"));

        doReturn(mockResponse).when(repository).save(any());

        // When
        final var response = underTest.addWeighEntry(request);

        // Then
        assertThat(response.getWeightTrackId().toString()).isEqualTo(mockResponse.getWeightTrackId().toString());
    }

    @Test
    void itShouldFailSavinInDatabase() {
        // Given
        final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final var now = "2016-11-09 10:30:22";
        final var formatDateTime = LocalDateTime.parse(now, formatter);
        final var request = new AddWeightTrackReqDTO();
        request.setWeight(100.0);
        request.setDate(formatDateTime);
        request.setClientId(UUID.fromString("5c6696ae-5f94-45d8-ab87-0754ba09e0e8"));

        // Mock
        doReturn(new RuntimeException()).when(repository).save(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.addWeighEntry(request))
                .isInstanceOf(FitnessErrorException.class);
    }
}
