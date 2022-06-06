package com.service.backend.logic;

import com.service.backend.exceptions.FitnessErrorException;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.AddGoalsResDTO;
import com.service.backend.repository.entities.GoalsEntity;

/**
 * @author Severiano Atencio
 */
public interface AddGoalsLogic {

    AddGoalsResDTO addGoals(AddGoalsReqDTO request) throws FitnessErrorException;

}
