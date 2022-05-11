package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;

/**
 * @author Severiano Atencio
 */
public interface SignInLogic {

    String getPassword(String username) throws FitnessErrorException;

    boolean validatePassword(String password, String hash) throws FitnessErrorException;

}
