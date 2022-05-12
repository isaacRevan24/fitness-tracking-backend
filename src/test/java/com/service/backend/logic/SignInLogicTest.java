package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
public class SignInLogicTest {

    @MockBean
    private ClientRepository repository;

    @Autowired
    private SignInLogic underTest;

    @Test
    void itShouldGetPasswordSuccessfully() {
        // Given
        final var username = "aaaaa";

        // Mock
        final var mockPassword = "password123";
        doReturn(mockPassword).when(repository).findUserByUserName(any());

        // When
        final var password = underTest.getPassword(username);

        // Then
        assertThat(password).isEqualTo(mockPassword);
    }

    @Test
    void itShouldFailGettingPassword() {
        // Given
        final var username = "aaaaa";

        // Mock
        doThrow(new FitnessErrorException()).when(repository).findUserByUserName(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.getPassword(username))
                .isInstanceOf(FitnessErrorException.class);

    }
}
