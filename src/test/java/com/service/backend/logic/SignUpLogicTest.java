package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.repository.ClientRepository;
import com.service.backend.repository.ClientValuesRepository;
import com.service.backend.util.crypto.EncryptionUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
class SignUpLogicTest {

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ClientValuesRepository clientValuesRepository;

    @Autowired
    private UserLogic underTest;

    @MockBean
    private EncryptionUtil encryptionUtil;

    @Test
    void itShouldSuccessfullyEncryptPasswords() throws Exception {
        // Given
        final var password = "password123";

        // Mock
        final var encodedString = Base64.getEncoder().encodeToString(password.getBytes());
        doReturn(encodedString).when(encryptionUtil).encryptPassword(any());

        // When
        var response = underTest.encryptPassword(password);

        // Then
        assertThat(response).isBase64();

    }

    @Test
    void itShouldFailEncryptPassword() throws Exception {
        // Given
        final var password = "password123";

        // Mock
        doThrow(new RuntimeException()).when(encryptionUtil).encryptPassword(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.encryptPassword(password))
                .isInstanceOf(FitnessErrorException.class);

    }
}
