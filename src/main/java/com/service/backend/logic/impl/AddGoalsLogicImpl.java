package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.AddGoalsLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.GoalsResDTO;
import com.service.backend.repository.GoalsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("AddGoalsLogicImpl")
public class AddGoalsLogicImpl implements AddGoalsLogic {

    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private FitnessMapper mapper;

    @Override
    public GoalsResDTO addGoals(AddGoalsReqDTO request) throws FitnessErrorException {

        final var methodName = "encryptPassword";

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

}
