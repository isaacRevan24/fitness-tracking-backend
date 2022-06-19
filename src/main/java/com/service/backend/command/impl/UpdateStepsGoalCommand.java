package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.GoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.StatusDTO;
import com.service.backend.model.UpdateStepsGoalReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.INTERNAL_ERROR;
import static com.service.backend.enums.StatusEnum.INVALID_STEP_GOAL;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("UpdateStepsGoalCommand")
public class UpdateStepsGoalCommand implements FitnessCommand<UpdateStepsGoalReqDTO, Void> {

    @Value("${steps.values.max}")
    private Integer stepsValues;

    @Autowired
    private FitnessMapper mapper;

    @Autowired
    private GoalsLogic logic;

    @Override
    public FitnessResponseEntity<Void> execute(FitnessRequestEntity<UpdateStepsGoalReqDTO> request) throws FitnessErrorException {
        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        var response = new FitnessResponseEntity<Void>();

        final var validation = validateStepsGoalValue(request.getBody().getStepsGoal());

        if (validation != null) {

            response.setStatus(validation);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return response;
        }

        response = updateStepsGoal(request.getBody());

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

        return response;
    }

    private FitnessResponseEntity<Void> updateStepsGoal(UpdateStepsGoalReqDTO request) {
        final var methodName = "execute";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var response = new FitnessResponseEntity<Void>();

        try {

            final var status = logic.updateStepsGoal(request.getClientId(), request.getStepsGoal());

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

    private StatusDTO validateStepsGoalValue(Integer value) {
        return (value < 0 || value > stepsValues) ? mapper.toStatusDTO(INVALID_STEP_GOAL) : null;
    }

}
