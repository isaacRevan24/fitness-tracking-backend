package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignUpLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.model.StatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignUpCommand")
public class SignUpCommand implements FitnessCommand<SignUpReqDTO, StatusDTO> {

    @Autowired
    private FitnessMapper mapper;

    @Autowired
    private SignUpLogic signUpLogic;

    @Override
    public FitnessResponseEntity<StatusDTO> execute(FitnessRequestEntity<SignUpReqDTO> request) throws FitnessErrorException {

        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = new FitnessResponseEntity<StatusDTO>();

        var logicResponse = logicExecution(request.getBody());

        response.setStatus(logicResponse);

        response.setBody(logicResponse);

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        return response;
    }

    private StatusDTO logicExecution(SignUpReqDTO request) {

        final var methodName = "logicExecution";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        StatusDTO status;

        try {

            signUpLogic.saveClient(request);

            status = mapper.toStatusDTO(StatusEnum.SUCCESS);

            log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

            return status;

        } catch (FitnessErrorException fitnessException) {

            status = new StatusDTO(fitnessException.getErrorCode(), fitnessException.getErrorMessage(),
                    fitnessException.getHttpError());

            log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

            return status;

        } catch (Exception exception) {

            status = mapper.toStatusDTO(StatusEnum.INTERNAL_ERROR);

            log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

            return status;

        }

    }

}
