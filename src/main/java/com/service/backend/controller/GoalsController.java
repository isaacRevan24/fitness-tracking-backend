package com.service.backend.controller;

import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.AddGoalsResDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FitnessMapper mapper;

    @PostMapping(path = "")
    public ResponseEntity<FitnessResponseEntity<AddGoalsResDTO>> addGoals(@Valid @RequestBody FitnessRequestEntity<AddGoalsReqDTO> body) {
        var response = new FitnessResponseEntity<AddGoalsResDTO>();
        response.setStatus(mapper.toStatusDTO(StatusEnum.SUCCESS));
        response.setBody(new AddGoalsResDTO(100.00, 12000));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
