package com.service.backend.util.crypto;

import com.service.backend.exceptions.FitnessErrorException;

/**
 * @author Severiano Atencio
 */
public interface EncryptionUtil {

    String encryptPassword(String password) throws FitnessErrorException;

    boolean validatePassword(String password, String hash) throws FitnessErrorException;

}
