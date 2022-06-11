package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.GoalsResDTO;

/**
 * @author Severiano Atencio
 */
public interface GetGoalsLogic {

    GoalsResDTO getGoals(String clientId) throws FitnessErrorException;

}
