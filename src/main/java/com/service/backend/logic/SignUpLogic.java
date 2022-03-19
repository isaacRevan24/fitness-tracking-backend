package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.SignUpReqDTO;

/**
 * @author Severiano Atencio
 */
public interface SignUpLogic {

    String encryptPassword(String password) throws FitnessErrorException;

    void signUp(SignUpReqDTO request) throws FitnessErrorException;

}
