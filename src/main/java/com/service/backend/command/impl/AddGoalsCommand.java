package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.AddGoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.GoalsResDTO;
import com.service.backend.model.StatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.INTERNAL_ERROR;
import static com.service.backend.enums.StatusEnum.SUCCESS;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("AddGoalsCommand")
public class AddGoalsCommand implements FitnessCommand<AddGoalsReqDTO, GoalsResDTO> {

    @Autowired
    private AddGoalsLogic logic;

    @Autowired
    private FitnessMapper mapper;

    @Override
    public FitnessResponseEntity<GoalsResDTO> execute(FitnessRequestEntity<AddGoalsReqDTO> request) throws FitnessErrorException {
        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = saveGoals(request.getBody());

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return response;
    }

    private FitnessResponseEntity<GoalsResDTO> saveGoals(AddGoalsReqDTO request) {
        final var methodName = "saveGoals";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = new FitnessResponseEntity<GoalsResDTO>();

        GoalsResDTO responseLogic;

        try {

            responseLogic = logic.addGoals(request);

            response.setBody(responseLogic);

        } catch (FitnessErrorException fitnessException) {

            response.setStatus(new StatusDTO(fitnessException.getErrorCode(), fitnessException.getErrorMessage(),
                    fitnessException.getHttpError()));

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;

        } catch (Exception exception) {

            response.setStatus(mapper.toStatusDTO(INTERNAL_ERROR));

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;

        }

        response.setStatus(mapper.toStatusDTO(SUCCESS));

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return response;

    }
}
