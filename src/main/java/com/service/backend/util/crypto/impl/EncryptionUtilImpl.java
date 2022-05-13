package com.service.backend.util.crypto.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.util.crypto.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("EncryptionUtilImpl")
public class EncryptionUtilImpl implements EncryptionUtil {

    private static final int ITERATIONS = 12;

    @Override
    public String encryptPassword(String password) throws Exception {

        final var methodName = "encryptPassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        var hash = BCrypt.hashpw(password, BCrypt.gensalt(ITERATIONS));

        var encodedHash = Base64.getEncoder().encodeToString(hash.getBytes());

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return encodedHash;
    }

    @Override
    public boolean validatePassword(String password, String hash) throws Exception {

        final var methodName = "validatePassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var decodedHash = new String(Base64.getDecoder().decode(hash));

        final var checkPassword = BCrypt.checkpw(password, decodedHash);

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return checkPassword;
    }

}
