package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignInLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignInReqDTO;
import com.service.backend.model.SignInResDTO;
import com.service.backend.model.StatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.*;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignInCommand")
public class SignInCommand implements FitnessCommand<SignInReqDTO, SignInResDTO> {

    @Autowired
    private FitnessMapper mapper;

    @Autowired
    private SignInLogic logic;

    @Override
    public FitnessResponseEntity<SignInResDTO> execute(FitnessRequestEntity<SignInReqDTO> request) throws FitnessErrorException {

        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = new FitnessResponseEntity<SignInResDTO>();

        final var passwordHash = getUserPassword(request.getBody().getUsername());

        if (passwordHash == null) {

            response.setStatus(mapper.toStatusDTO(ERROR_GETTING_PASSWORD));

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;
        }

        final var validation = validatePassword(request.getBody().getPassword(), passwordHash);

        if (validation == null) {

            response.setStatus(mapper.toStatusDTO(ERROR_VALIDATION_PASSWORD));

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;

        }

        if (!validation) {

            response.setStatus(mapper.toStatusDTO(SIGN_IN_ERROR));

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;
        }

        response.setStatus(mapper.toStatusDTO(SUCCESS));

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return response;
    }

    private String getUserPassword(String username) {

        final var methodName = "getUserPassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        String password;

        try {
            password = logic.getPassword(username);
        }catch (Exception exception) {

            log.error("Message: " + exception.getMessage());
            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return null;

        }

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return password;

    }

    private Boolean validatePassword(String password, String hash) {

        final var methodName = "validatePassword";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        boolean valid;

        try {
            valid = logic.validatePassword(password, hash);
        }catch (Exception exception) {

            log.error("Message: " + exception.getMessage());
            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return null;

        }

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        return valid;

    }

}
