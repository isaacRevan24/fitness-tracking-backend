package com.service.backend.controller;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.BaseResponseEntity;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.GoalsResDTO;
import com.service.backend.model.UpdateStepsGoalReq;
import com.service.backend.model.UpdateWeightGoalReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Severiano Atencio
 */
@RestController
@RequestMapping("/goals")
public class GoalsController {

    @Autowired
    @Qualifier("AddGoalsCommand")
    private FitnessCommand<AddGoalsReqDTO, GoalsResDTO> addGoalsCommand;

    @Autowired
    @Qualifier("GetGoalsCommand")
    private FitnessCommand<String, GoalsResDTO> getGoalsCommand;

    @Autowired
    @Qualifier("UpdateWeightGoalsCommand")
    private FitnessCommand<UpdateWeightGoalReq, Void> updateWeightGoalCommand;

    @Autowired
    @Qualifier("UpdateStepsGoalCommand")
    private FitnessCommand<UpdateStepsGoalReq, Void> updateStepsGoalCommand;

    @Autowired
    private FitnessMapper mapper;

    @PostMapping(path = "")
    public ResponseEntity<FitnessResponseEntity<GoalsResDTO>> addGoals(@Valid @RequestBody FitnessRequestEntity<AddGoalsReqDTO> body) {
        final var request = new FitnessRequestEntity<AddGoalsReqDTO>();
        request.setBody(body.getBody());
        final var response = addGoalsCommand.execute(request);
        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FitnessResponseEntity<GoalsResDTO>> getGoals(@PathVariable String id) {
        final var request = new FitnessRequestEntity<String>();
        request.setBody(id);
        final var response = getGoalsCommand.execute(request);
        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }

    @PutMapping(path = "/weight")
    public ResponseEntity<BaseResponseEntity> updateWeightGoal(@Valid @RequestBody FitnessRequestEntity<UpdateWeightGoalReq> body) {
        final var request = new FitnessRequestEntity<UpdateWeightGoalReq>();
        request.setBody(body.getBody());
        final var response = updateWeightGoalCommand.execute(request);
        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }

    @PutMapping(path = "/steps")
    public ResponseEntity<BaseResponseEntity> updateStepsGoal(@Valid @RequestBody FitnessRequestEntity<UpdateStepsGoalReq> body) {
        final var request = new FitnessRequestEntity<UpdateStepsGoalReq>();
        request.setBody(body.getBody());
        final var response = updateStepsGoalCommand.execute(request);
        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }
}
