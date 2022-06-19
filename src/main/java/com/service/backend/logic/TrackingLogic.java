package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.AddWeightTrackReqDTO;

/**
 * @author Severiano Atencio
 */
public interface TrackingLogic {

    void addWeighEntry(AddWeightTrackReqDTO request) throws FitnessErrorException;

}
