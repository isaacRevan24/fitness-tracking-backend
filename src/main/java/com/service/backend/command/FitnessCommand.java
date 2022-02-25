package com.service.backend.command;

import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.exceptions.FitnessErrorException;

/**
 * @author Severiano Atencio
 */
public interface FitnessCommand<T, R> {

    FitnessResponseEntity<R> execute(FitnessRequestEntity<T> request) throws FitnessErrorException;

}
