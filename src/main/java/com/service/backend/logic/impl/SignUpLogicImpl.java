package com.service.backend.logic.impl;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.logic.SignUpLogic;
import com.service.backend.model.SignUpReqDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Severiano Atencio
 */
@Slf4j
@Component("SignUpLogicImpl")
public class SignUpLogicImpl implements SignUpLogic {

    @Override
    public void SaveNewClient(SignUpReqDTO request) throws FitnessErrorException {
        
    }

}
