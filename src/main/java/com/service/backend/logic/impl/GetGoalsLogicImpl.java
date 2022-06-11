package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.GetGoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.GoalsResDTO;
import com.service.backend.repository.GoalsRepository;
import com.service.backend.repository.entities.GoalsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("GetGoalsLogicImpl")
public class GetGoalsLogicImpl implements GetGoalsLogic {

    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private FitnessMapper mapper;

    @Override
    public GoalsResDTO getGoals(String clientId) throws FitnessErrorException {

        final var methodName = "getGoals";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        GoalsEntity goals;

        try {

            goals = goalsRepository.fetchUserGoals(clientId);

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

}
