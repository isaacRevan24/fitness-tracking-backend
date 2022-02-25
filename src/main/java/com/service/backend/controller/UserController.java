package com.service.backend.controller;

import com.service.backend.controller.entity.BaseResponseEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Severiano Atencio
 */
@RestController
@RequestMapping("v1/fitness")
public class UserController {

    @Autowired
    private FitnessMapper mapper;

    @GetMapping(path = "/health-check")
    public ResponseEntity<BaseResponseEntity> healthCheck() {

        var status = mapper.toStatusDTO(StatusEnum.SUCCESS);
        var response = new BaseResponseEntity(status);

        return ResponseEntity.status(status.getHttpStatus()).body(response);

    }
}
