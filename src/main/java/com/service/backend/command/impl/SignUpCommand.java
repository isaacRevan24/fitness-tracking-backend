package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
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
@Component
public class SignUpCommand implements FitnessCommand<SignUpReqDTO, StatusDTO> {

    @Autowired
    private FitnessMapper mapper;

    @Override
    public FitnessResponseEntity<StatusDTO> execute(FitnessRequestEntity<SignUpReqDTO> request) throws FitnessErrorException {

        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        var response = new FitnessResponseEntity<StatusDTO>();

        response.setStatus(mapper.toStatusDTO(StatusEnum.SUCCESS));

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        return response;
    }

}
