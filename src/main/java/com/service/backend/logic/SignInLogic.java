package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.repository.entities.ClientEntity;

/**
 * @author Severiano Atencio
 */
public interface SignInLogic {

    ClientEntity getUser(String username) throws FitnessErrorException;

    boolean validatePassword(String password, String hash) throws FitnessErrorException;

}
