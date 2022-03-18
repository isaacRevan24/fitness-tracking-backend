package com.service.backend.util.crypto.impl;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.util.crypto.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("EncryptionUtilImpl")
public class EncryptionUtilImpl implements EncryptionUtil {

    @Override
    public String encryptPassword(String password) throws FitnessErrorException {
        return null;
    }

    @Override
    public boolean validatePassword(String password, String hash) throws FitnessErrorException {
        return false;
    }

}
