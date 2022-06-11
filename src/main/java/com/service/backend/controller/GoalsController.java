package com.service.backend.controller;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.AddGoalsResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    private FitnessCommand<AddGoalsReqDTO, AddGoalsResDTO> addGoalsCommand;

    @Autowired
    private FitnessMapper mapper;

    @PostMapping(path = "")
    public ResponseEntity<FitnessResponseEntity<AddGoalsResDTO>> addGoals(@Valid @RequestBody FitnessRequestEntity<AddGoalsReqDTO> body) {
        final var request = new FitnessRequestEntity<AddGoalsReqDTO>();
        request.setBody(body.getBody());
        final var response = addGoalsCommand.execute(request);
        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }
}
