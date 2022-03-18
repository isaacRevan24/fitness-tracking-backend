package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignUpLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.repository.ClientRepository;
import com.service.backend.repository.ClientValuesRepository;
import com.service.backend.repository.entities.ClientEntity;
import com.service.backend.util.crypto.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static com.service.backend.enums.StatusEnum.ENCRYPTION_EXCEPTION;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignUpLogicImpl")
public class SignUpLogicImpl implements SignUpLogic {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientValuesRepository clientValuesRepository;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Autowired
    private FitnessMapper mapper;

    @Override
    public ClientEntity saveClient(SignUpReqDTO request) throws FitnessErrorException {

        final var methodName = "saveClient";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var clientEntity = mapper.toClientEntity(request);

        try {

            final var client = clientRepository.save(clientEntity);

            log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

            return client;

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

    }

    @Override
    public void saveClientValues(SignUpReqDTO request, UUID id) throws FitnessErrorException {

        final var methodName = "saveClientValues";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var clientValues = mapper.toClientValuesEntity(request, id);

        try {

            clientValuesRepository.save(clientValues);

            log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

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

    }

    @Override
    public String encryptPassword(String password) throws FitnessErrorException {

        final var methodName = "encryptPassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        String encryptedPassword;

        try {

            encryptedPassword = encryptionUtil.encryptPassword(password);

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

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        return encryptedPassword;
    }

}
