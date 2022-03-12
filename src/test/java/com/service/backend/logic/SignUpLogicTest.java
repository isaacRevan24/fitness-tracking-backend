package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.repository.ClientRepository;
import com.service.backend.repository.ClientValuesRepository;
import com.service.backend.repository.entities.ClientEntity;
import com.service.backend.repository.entities.ClientValuesEntity;
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
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {FitnessTrackingApplication.class}, properties = "spring.main.allow-bean-definition-overriding=true")
class SignUpLogicTest {

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ClientValuesRepository clientValuesRepository;

    @Autowired
    private SignUpLogic underTest;

    @Test
    void itShouldSaveNewClientSuccessfully() {
        // Given
        var request = new SignUpReqDTO();
        request.setAge(19);
        request.setUsername("isaac123");
        request.setPassword("abc123");

        // Mock
        var mockResponse = new ClientEntity();
        doReturn(mockResponse).when(clientRepository).save(any());

        // When
        var response = underTest.saveClient(request);

        // Then
        assertThat(response).isNotNull();
    }

    @Test
    void itShouldFailBecauseExceptionOnSaving() {
        // Given
        var request = new SignUpReqDTO();
        request.setAge(19);
        request.setUsername("isaac123");
        request.setPassword("abc123");

        // Mock
        doThrow(new RuntimeException()).when(clientRepository).save(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.saveClient(request)).isInstanceOf(FitnessErrorException.class);

    }

    @Test
    void itShouldSuccessfullySaveClientValues() {
        // Given
        var request = new SignUpReqDTO();
        request.setAge(19);
        request.setUsername("isaac123");
        request.setPassword("abc123");
        request.setHeight(1.85);
        request.setWeight(112.0);
        request.setHeightMetric("mt");
        request.setWeightMetric("kg");
        var uuid = UUID.randomUUID();

        // Mock
        var mockResponse = new ClientValuesEntity();
        doReturn(mockResponse).when(clientValuesRepository).save(any());

        // When
        underTest.saveClientValues(request, uuid);

        // Then
        verify(clientValuesRepository).save(any());

    }

    @Test
    void itShouldFailBecauseException() {
        // Given
        var request = new SignUpReqDTO();
        request.setAge(19);
        request.setUsername("isaac123");
        request.setPassword("abc123");
        request.setHeight(1.85);
        request.setWeight(112.0);
        request.setHeightMetric("mt");
        request.setWeightMetric("kg");
        var uuid = UUID.randomUUID();

        // Mock
        doThrow(new RuntimeException()).when(clientValuesRepository).save(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.saveClientValues(request, uuid)).isInstanceOf(FitnessErrorException.class);
    }
}
