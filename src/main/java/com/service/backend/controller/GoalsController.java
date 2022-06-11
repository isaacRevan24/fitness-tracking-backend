package com.service.backend.controller;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.BaseResponseEntity;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.GoalsResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private FitnessCommand<AddGoalsReqDTO, GoalsResDTO> addGoalsCommand;

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

        var response = new FitnessResponseEntity<GoalsResDTO>();
        response.setBody(new GoalsResDTO(95.00, 12000));
        response.setStatus(mapper.toStatusDTO(StatusEnum.SUCCESS));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
