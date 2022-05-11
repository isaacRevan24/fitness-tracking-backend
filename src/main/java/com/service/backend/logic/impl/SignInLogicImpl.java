package com.service.backend.logic.impl;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignInLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignInLogicImpl")
public class SignInLogicImpl implements SignInLogic {

    @Override
    public String getPassword(String username) throws FitnessErrorException {
        return null;
    }

    @Override
    public boolean validatePassword(String password, String hash) throws FitnessErrorException {
        return false;
    }
}
