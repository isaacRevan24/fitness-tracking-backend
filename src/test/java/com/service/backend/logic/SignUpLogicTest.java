package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {FitnessTrackingApplication.class}, properties = "spring.main.allow-bean-definition-overriding=true")
class SignUpLogicTest {

    @MockBean
    private ClientRepository repository;

    @Autowired
    private SignUpLogic underTest;

    @Test
    void itShouldSaveNewClientSuccessfully() {
        // Given
        var request =  SignUpReqDTO.builder().username("isaac123").password("password134").age(19).build();

        // When
        underTest.saveClient(request);

        // Then
        verify(repository).save(any());

    }

    @Test
    void itShouldFailBecauseExceptionOnSaving() {
        // Given
        var request =  SignUpReqDTO.builder().username("isaac123").password("password134").age(19).build();

        // Mock
        doThrow(new RuntimeException()).when(repository).save(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.saveClient(request)).isInstanceOf(FitnessErrorException.class);

    }
}
