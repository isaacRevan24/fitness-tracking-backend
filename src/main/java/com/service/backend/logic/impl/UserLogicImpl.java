package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.UserLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.repository.ClientRepository;
import com.service.backend.repository.ClientValuesRepository;
import com.service.backend.repository.entities.ClientEntity;
import com.service.backend.util.crypto.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static com.service.backend.enums.StatusEnum.ENCRYPTION_EXCEPTION;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignUpLogicImpl")
public class UserLogicImpl implements UserLogic {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientValuesRepository clientValuesRepository;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Autowired
    private FitnessMapper mapper;

    @Override
    public String encryptPassword(String password) throws FitnessErrorException {

        final var methodName = "encryptPassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        String encryptedPassword;

        try {

            encryptedPassword = encryptionUtil.encryptPassword(password);

        } catch (Exception exception) {

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

        return encryptedPassword;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, FitnessErrorException.class})
    public void signUp(SignUpReqDTO request) throws FitnessErrorException {

        final var methodName = "encryptPassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var clientEntity = mapper.toClientEntity(request);

        final var client = repository.save(clientEntity);

        clientValuesRepository.save(mapper.toClientValuesEntity(request, client.getId()));

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);
    }

    @Override
    public ClientEntity getUser(String username) throws FitnessErrorException {

        final var methodName = "getPassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        ClientEntity client;

        try {

            client = repository.findUserByUserName(username);

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

        return client;
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
