package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.enums.StatusEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignInReqDTO;
import com.service.backend.model.SignInResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignInCommand")
public class SignInCommand implements FitnessCommand<SignInReqDTO, SignInResDTO> {

    @Autowired
    private FitnessMapper mapper;

    @Override
    public FitnessResponseEntity<SignInResDTO> execute(FitnessRequestEntity<SignInReqDTO> request) throws FitnessErrorException {

        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = new FitnessResponseEntity<SignInResDTO>();

        response.setStatus(mapper.toStatusDTO(StatusEnum.SUCCESS));
        response.setBody(new SignInResDTO("eba73837-16b0-4b9b-afa2-2df8a8a7ed8b"));

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        return response;
    }

}
