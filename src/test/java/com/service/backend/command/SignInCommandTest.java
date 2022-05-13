package com.service.backend.command;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignInLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignInReqDTO;
import com.service.backend.model.SignInResDTO;
import com.service.backend.repository.entities.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
class SignInCommandTest {

    @Autowired
    private FitnessCommand<SignInReqDTO, SignInResDTO> underTest;

    @Autowired
    private FitnessMapper mapper;

    @MockBean
    private SignInLogic logic;

    @Test
    void itShouldExecuteSignInCommandSuccessfully() {
        // Given
        final var request = new FitnessRequestEntity<SignInReqDTO>();
        final var requestBody = new SignInReqDTO();
        requestBody.setPassword("password123");
        requestBody.setUsername("user1");
        request.setBody(requestBody);

        // Mock
        final var mockClient = new ClientEntity();
        mockClient.setPassword("password123");
        mockClient.setId(UUID.fromString("c08cb8f4-ca26-4635-8f13-0270ef8ea371"));
        doReturn(mockClient).when(logic).getUser(anyString());
        doReturn(true).when(logic).validatePassword(anyString(), anyString());

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(StatusEnum.SUCCESS.getCode());
        assertThat(response.getBody().getUserId()).isEqualTo("c08cb8f4-ca26-4635-8f13-0270ef8ea371");
    }

    @Test
    void itShouldFailBecauseErrorGettingUserFromDataBase() {
        // Given
        final var request = new FitnessRequestEntity<SignInReqDTO>();
        final var requestBody = new SignInReqDTO();
        requestBody.setPassword("password123");
        requestBody.setUsername("user1");
        request.setBody(requestBody);

        // Mock
        doThrow(new FitnessErrorException()).when(logic).getUser(anyString());

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(StatusEnum.ERROR_GETTING_USER.getCode());
    }

    @Test
    void itShouldFailBecauseErrorValidatingPassword() {
        // Given
        final var request = new FitnessRequestEntity<SignInReqDTO>();
        final var requestBody = new SignInReqDTO();
        requestBody.setPassword("password123");
        requestBody.setUsername("user1");
        request.setBody(requestBody);

        // Mock
        final var mockClient = new ClientEntity();
        mockClient.setPassword("password123");
        mockClient.setId(UUID.fromString("c08cb8f4-ca26-4635-8f13-0270ef8ea371"));
        doReturn(mockClient).when(logic).getUser(anyString());
        doThrow(new FitnessErrorException()).when(logic).validatePassword(anyString(), anyString());

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(StatusEnum.ERROR_VALIDATION_PASSWORD.getCode());
    }

    @Test
    void itShouldFailSignInBecausePasswordNotValid() {
        // Given
        final var request = new FitnessRequestEntity<SignInReqDTO>();
        final var requestBody = new SignInReqDTO();
        requestBody.setPassword("password123");
        requestBody.setUsername("user1");
        request.setBody(requestBody);

        // Mock
        final var mockClient = new ClientEntity();
        mockClient.setPassword("password123");
        mockClient.setId(UUID.fromString("c08cb8f4-ca26-4635-8f13-0270ef8ea371"));
        doReturn(mockClient).when(logic).getUser(anyString());
        doReturn(false).when(logic).validatePassword(anyString(), anyString());

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(StatusEnum.SIGN_IN_ERROR.getCode());
    }
}
