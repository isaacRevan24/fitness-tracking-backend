package com.service.backend.controller;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.BaseResponseEntity;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.model.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Severiano Atencio
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private FitnessMapper mapper;

    @Autowired
    @Qualifier("SignUpCommand")
    private FitnessCommand<SignUpReqDTO, StatusDTO> signUpCommand;

    @GetMapping(path = "/health-check")
    public ResponseEntity<BaseResponseEntity> healthCheck() {

        var status = mapper.toStatusDTO(StatusEnum.SUCCESS);
        var response = new BaseResponseEntity(status);

        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<BaseResponseEntity> signUp(@Valid @RequestBody FitnessRequestEntity<SignUpReqDTO> body) {

        var request = new FitnessRequestEntity<SignUpReqDTO>();

        request.setBody(body.getBody());

        var response = signUpCommand.execute(request);

        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }
}
