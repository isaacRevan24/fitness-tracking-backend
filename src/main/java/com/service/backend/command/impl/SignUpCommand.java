package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.UserLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.model.StatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.regex.Pattern;

import static com.service.backend.enums.StatusEnum.INVALID_PASSWORD_FORMAT;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignUpCommand")
public class SignUpCommand implements FitnessCommand<SignUpReqDTO, StatusDTO> {

    @Autowired
    private FitnessMapper mapper;

    @Autowired
    private UserLogic userLogic;

    @Value( "${password.regex}" )
    private String passwordRegex;

    @Override
    public FitnessResponseEntity<StatusDTO> execute(FitnessRequestEntity<SignUpReqDTO> request) throws FitnessErrorException {

        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = new FitnessResponseEntity<StatusDTO>();

        final var passwordValidation = passwordValidation(request.getBody().getPassword());

        if (passwordValidation != null) {

            response.setStatus(passwordValidation);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;

        }

        var logicResponse = logicExecution(request.getBody());

        response.setStatus(logicResponse);

        response.setBody(logicResponse);

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return response;
    }

    private StatusDTO passwordValidation(String password) {

        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var decodedBytes = Base64.getDecoder().decode(passwordRegex);

        final var decodedString = new String(decodedBytes);

        final var pattern = Pattern.compile(decodedString);

        if (!pattern.matcher(password).matches()) {

            var status = mapper.toStatusDTO(INVALID_PASSWORD_FORMAT);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return status;
        }

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return null;

    }

    private StatusDTO logicExecution(SignUpReqDTO request) {

        final var methodName = "logicExecution";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        StatusDTO status;

        try {

            request.setPassword(userLogic.encryptPassword(request.getPassword()));

            userLogic.signUp(request);

            status = mapper.toStatusDTO(StatusEnum.SUCCESS);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return status;

        } catch (FitnessErrorException fitnessException) {

            status = new StatusDTO(fitnessException.getErrorCode(), fitnessException.getErrorMessage(),
                    fitnessException.getHttpError());

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return status;

        } catch (Exception exception) {

            status = mapper.toStatusDTO(StatusEnum.DATABASE_ERROR);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return status;

        }

    }

}
