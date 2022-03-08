package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.SignUpReqDTO;

/**
 * @author Severiano Atencio
 */
public interface SignUpLogic {

    void saveNewClient(SignUpReqDTO request) throws FitnessErrorException;

}
