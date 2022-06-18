package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.GoalsResDTO;

/**
 * @author Severiano Atencio
 */
public interface GoalsLogic {

    GoalsResDTO addGoals(AddGoalsReqDTO request) throws FitnessErrorException;

}
