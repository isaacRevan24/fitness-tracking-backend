package com.service.backend.logic.impl;

import com.service.backend.enums.GenericLogEnum;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.TrackingLogic;
import com.service.backend.model.AddWeightTrackReqDTO;
import com.service.backend.repository.WeightTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("TrackingLogicImpl")
public class TrackingLogicImpl implements TrackingLogic {

    @Autowired
    private WeightTrackRepository repository;

    @Override
    public void addWeighEntry(AddWeightTrackReqDTO request) throws FitnessErrorException {

        final var methodName = "addWeighEntry";

        log.debug(GenericLogEnum.START_MESSAGE.getMessage() + methodName);

        log.debug(GenericLogEnum.FINISH_MESSAGE.getMessage() + methodName);

    }

}
