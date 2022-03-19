package com.service.backend.command;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignUpLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.model.StatusDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Base64;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
class SignUpCommandTest {

    @Autowired
    private FitnessCommand<SignUpReqDTO, StatusDTO> underTest;

    @MockBean
    private SignUpLogic signUpLogic;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldExecuteSuccessfully() {
        // Given
        var request = new FitnessRequestEntity<SignUpReqDTO>();
        var requestBody = new SignUpReqDTO();
        requestBody.setPassword("Password123");
        request.setBody(requestBody);

        // Mock
        final var encodedString = Base64.getEncoder().encodeToString(requestBody.getPassword().getBytes());
        doReturn(encodedString).when(signUpLogic).encryptPassword(any());

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(StatusEnum.SUCCESS.getCode());

    }

    @Test
    void itShouldFailInLogicBecauseDatabaseError() {
        // Given
        var request = new FitnessRequestEntity<SignUpReqDTO>();
        var requestBody = new SignUpReqDTO();
        requestBody.setPassword("Password123");
        request.setBody(requestBody);

        // Mock
        doThrow(new FitnessErrorException(DATABASE_ERROR.getCode(), DATABASE_ERROR.getMessage(),
                DATABASE_ERROR.getStatus())).when(signUpLogic).signUp(any());

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(StatusEnum.DATABASE_ERROR.getCode());

    }

    @Test
    void itShouldFailBecauseException() {
        // Given
        var request = new FitnessRequestEntity<SignUpReqDTO>();
        var requestBody = new SignUpReqDTO();
        requestBody.setPassword("Password123");
        request.setBody(requestBody);

        // Mock
        doThrow(new RuntimeException()).when(signUpLogic).signUp(any());

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(DATABASE_ERROR.getCode());

    }

}
