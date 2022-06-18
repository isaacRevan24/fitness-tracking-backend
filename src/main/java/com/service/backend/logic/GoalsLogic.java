package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.GoalsResDTO;
import com.service.backend.model.StatusDTO;

import java.util.UUID;

/**
 * @author Severiano Atencio
 */
public interface GoalsLogic {

    GoalsResDTO addGoals(AddGoalsReqDTO request) throws FitnessErrorException;

    GoalsResDTO getGoals(String clientId) throws FitnessErrorException;

    StatusDTO updateWeightGoal(UUID clientId, Double weight) throws FitnessErrorException;
    
    StatusDTO updateStepsGoal(UUID clientId, Integer steps) throws FitnessErrorException;

}
