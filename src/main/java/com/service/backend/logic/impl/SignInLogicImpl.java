package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignInLogic;
import com.service.backend.repository.ClientRepository;
import com.service.backend.util.crypto.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static com.service.backend.enums.StatusEnum.ENCRYPTION_EXCEPTION;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignInLogicImpl")
public class SignInLogicImpl implements SignInLogic {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Override
    public String getPassword(String username) throws FitnessErrorException {

        final var methodName = "getPassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        String password;

        try {

            password = repository.findUserByUserName(username);

        } catch (Exception exception) {

            log.error(exception.getMessage());

            throw new FitnessErrorException(
                    exception.getMessage(),
                    exception,
                    DATABASE_ERROR.getCode(),
                    DATABASE_ERROR.getMessage(),
                    DATABASE_ERROR.getStatus()
            );

        }

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return password;
    }

    @Override
    public boolean validatePassword(String password, String hash) throws FitnessErrorException {

        final var methodName = "validatePassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        boolean validation;

        try {
            validation = encryptionUtil.validatePassword(password, hash);
        }catch (Exception exception) {

            log.error(exception.getMessage());

            throw new FitnessErrorException(
                    exception.getMessage(),
                    exception,
                    ENCRYPTION_EXCEPTION.getCode(),
                    ENCRYPTION_EXCEPTION.getMessage(),
                    ENCRYPTION_EXCEPTION.getStatus()
            );

        }

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return validation;
    }

}
