package com.service.backend.util.crypto;

import com.service.backend.FitnessTrackingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {FitnessTrackingApplication.class}, properties = "spring.main.allow-bean-definition-overriding=true")
class EncryptionUtilTest {

    @Autowired
    private EncryptionUtil underTest;

    @Test
    void itShouldGenerateHashedPassword() throws Exception {
        // Given
        final var password = "Password123*";

        // When
        var response = underTest.encryptPassword(password);

        // Then
        assertThat(response).isBase64();

    }
}
