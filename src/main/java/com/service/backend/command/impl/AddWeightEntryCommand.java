package com.service.backend.command.impl;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.AddWeightTrackReqDTO;
import com.service.backend.model.AddWeightTrackResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("AddWeightEntryCommand")
public class AddWeightEntryCommand implements FitnessCommand<AddWeightTrackReqDTO, AddWeightTrackResDTO> {

    @Override
    public FitnessResponseEntity<AddWeightTrackResDTO> execute(FitnessRequestEntity<AddWeightTrackReqDTO> request) throws FitnessErrorException {
        return null;
    }

}
