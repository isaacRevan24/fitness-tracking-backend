package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.repository.entities.ClientEntity;

/**
 * @author Severiano Atencio
 */
public interface SignUpLogic {

    ClientEntity saveClient(SignUpReqDTO request) throws FitnessErrorException;

    void saveClientValues(SignUpReqDTO request) throws FitnessErrorException;

}
