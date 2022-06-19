package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.GoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.GoalsResDTO;
import com.service.backend.model.StatusDTO;
import com.service.backend.repository.GoalsRepository;
import com.service.backend.repository.entities.GoalsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;
import static com.service.backend.enums.StatusEnum.ERROR_UPDATING_STEPS;
import static com.service.backend.enums.StatusEnum.ERROR_UPDATING_WEIGHT;
import static com.service.backend.enums.StatusEnum.SUCCESS;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("AddGoalsLogicImpl")
public class GoalsLogicImpl implements GoalsLogic {

    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private FitnessMapper mapper;

    @Override
    public GoalsResDTO addGoals(AddGoalsReqDTO request) throws FitnessErrorException {

        final var methodName = "addGoals";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var goalsEntity = mapper.toGoalsEntity(request);

        try {

            goalsRepository.save(goalsEntity);

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

        return mapper.toAddGoalResp(goalsEntity);

    }

    @Override
    public GoalsResDTO getGoals(String clientId) throws FitnessErrorException {

        final var methodName = "getGoals";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        GoalsEntity goals;

        try {

            goals = goalsRepository.fetchUserGoals(UUID.fromString(clientId));

        }catch (Exception exception) {

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

        return mapper.toAddGoalResp(goals);
    }

    @Override
    public StatusDTO updateWeightGoal(UUID clientId, Double weight) throws FitnessErrorException {

        final var methodName = "updateWeightGoal";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        try {

            int updated = goalsRepository.updateWeighGoal(clientId, weight);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            if(updated < 1) return mapper.toStatusDTO(ERROR_UPDATING_WEIGHT);

            return mapper.toStatusDTO(SUCCESS);

        }catch (Exception exception) {

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
    public StatusDTO updateStepsGoal(UUID clientId, Integer steps) throws FitnessErrorException {
        final var methodName = "updateStepsGoal";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        try {

            int updated = goalsRepository.updateStepsGoal(clientId, steps);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            if(updated < 1) return mapper.toStatusDTO(ERROR_UPDATING_STEPS);

            return mapper.toStatusDTO(SUCCESS);

        }catch (Exception exception) {

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

}
