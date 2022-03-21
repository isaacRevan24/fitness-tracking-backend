package com.service.backend.command;

import com.service.backend.FitnessTrackingApplication;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignInReqDTO;
import com.service.backend.model.SignInResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {FitnessTrackingApplication.class},
        properties = "spring.main.allow-bean-definition-overriding=true")
class SignInCommandTest {

    @Autowired
    private FitnessCommand<SignInReqDTO, SignInResDTO> underTest;

    @Autowired
    private FitnessMapper mapper;

    @Test
    void itShouldExecuteSignInCommandSuccessfully() {
        // Given
        final var request = new FitnessRequestEntity<SignInReqDTO>();
        final var requestBody = new SignInReqDTO();
        requestBody.setPassword("password123");
        requestBody.setUsername("user1");
        request.setBody(requestBody);

        // When
        var response = underTest.execute(request);

        // Then
        assertThat(response.getStatus().getCode()).isEqualTo(StatusEnum.SUCCESS.getCode());

    }
}
