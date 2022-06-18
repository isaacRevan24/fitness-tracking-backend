package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.GoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.StatusDTO;
import com.service.backend.model.UpdateWeightGoalReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.INTERNAL_ERROR;
import static com.service.backend.enums.StatusEnum.INVALID_WEIGHT_GOAL;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("UpdateWeightGoalsCommand")
public class UpdateWeightGoalsCommand implements FitnessCommand<UpdateWeightGoalReq, Void> {

    @Autowired
    private GoalsLogic logic;

    @Autowired
    private FitnessMapper mapper;

    @Value("${weight.values.max}")
    private Double weightValues;

    @Override
    public FitnessResponseEntity<Void> execute(FitnessRequestEntity<UpdateWeightGoalReq> request) throws FitnessErrorException {
        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        var response = new FitnessResponseEntity<Void>();

        final var validation = validateWeightGoalValue(request.getBody().getWeightGoal());

        if (validation != null) {

            response.setStatus(validation);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;
        }

        response = updateWeightGoal(request.getBody());

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return response;
    }

    private FitnessResponseEntity<Void> updateWeightGoal(UpdateWeightGoalReq request) {
        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = new FitnessResponseEntity<Void>();

        try {

            final var status = logic.updateWeightGoal(request.getClientId(), request.getWeightGoal());

            response.setStatus(status);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;

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
    }

    private StatusDTO validateWeightGoalValue(Double value) {
        return (value < 0 || value > weightValues) ? mapper.toStatusDTO(INVALID_WEIGHT_GOAL) : null;
    }
}
