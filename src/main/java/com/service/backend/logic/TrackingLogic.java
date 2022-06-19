package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.AddWeightTrackReqDTO;
import com.service.backend.model.AddWeightTrackResDTO;

/**
 * @author Severiano Atencio
 */
public interface TrackingLogic {

    AddWeightTrackResDTO addWeighEntry(AddWeightTrackReqDTO request) throws FitnessErrorException;

}
