package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.repository.entities.ClientEntity;

import java.util.UUID;

/**
 * @author Severiano Atencio
 */
public interface SignUpLogic {

    ClientEntity saveClient(SignUpReqDTO request) throws FitnessErrorException;

    void saveClientValues(SignUpReqDTO request, UUID id) throws FitnessErrorException;

    String encryptPassword(String password) throws FitnessErrorException;

}
