package com.service.backend.logic;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.repository.ClientRepository;
import com.service.backend.repository.entities.ClientEntity;
import com.service.backend.util.crypto.EncryptionUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
public class SignInLogicTest {

    @MockBean
    private ClientRepository repository;

    @MockBean
    private EncryptionUtil encryptionUtil;

    @Autowired
    private UserLogic underTest;

    @Test
    void itShouldGetPasswordSuccessfully() {
        // Given
        final var username = "aaaaa";

        // Mock
        final var mockUser = new ClientEntity();
        mockUser.setPassword("password123");
        doReturn(mockUser).when(repository).findUserByUserName(any());

        // When
        final var password = underTest.getUser(username);

        // Then
        assertThat(password.getPassword()).isEqualTo(mockUser.getPassword());
    }

    @Test
    void itShouldFailGettingPassword() {
        // Given
        final var username = "aaaaa";

        // Mock
        doThrow(new FitnessErrorException()).when(repository).findUserByUserName(any());

        // When
        // Then
        assertThatThrownBy(() -> underTest.getUser(username))
                .isInstanceOf(FitnessErrorException.class);

    }

    @Test
    void itShouldValidatePasswordSuccessfully() throws Exception {
        // Given
        final var password = "password123";
        final var hash = "slfjasldfnlnrelrh,sdmnflñasjhf9qwerb";

        // Mock
        doReturn(true).when(encryptionUtil).validatePassword(anyString(), anyString());

        // When
        final var response  = underTest.validatePassword(password, hash);

        // Then
        assertThat(response).isTrue();
    }

    @Test
    void itShouldFailValidationPassword() throws Exception {
        // Given
        final var password = "password123";
        final var hash = "slfjasldfnlnrelrh,sdmnflñasjhf9qwerb";

        // Mock
        doThrow(new FitnessErrorException()).when(encryptionUtil).validatePassword(anyString(), anyString());

        // When
        // Then
        assertThatThrownBy(() -> underTest.validatePassword(password, hash))
                .isInstanceOf(FitnessErrorException.class);
    }
}
