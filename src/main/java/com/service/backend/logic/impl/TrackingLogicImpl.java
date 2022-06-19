package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.TrackingLogic;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddWeightTrackReqDTO;
import com.service.backend.model.AddWeightTrackResDTO;
import com.service.backend.repository.WeightTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.service.backend.enums.StatusEnum.DATABASE_ERROR;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("TrackingLogicImpl")
public class TrackingLogicImpl implements TrackingLogic {

    @Autowired
    private WeightTrackRepository repository;

    @Autowired
    private FitnessMapper mapper;

    @Override
    public AddWeightTrackResDTO addWeighEntry(AddWeightTrackReqDTO request) throws FitnessErrorException {

        final var methodName = "addWeighEntry";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        final var entity = mapper.toWeightTrackEntity(request);

        try {

            final var values = repository.save(entity);

            log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

            return mapper.toAddWeightTrackResponse(values);

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

    }

}
