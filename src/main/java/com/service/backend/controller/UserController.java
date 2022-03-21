package com.service.backend.controller;

import com.service.backend.command.FitnessCommand;
import com.service.backend.controller.entity.BaseResponseEntity;
import com.service.backend.controller.entity.FitnessRequestEntity;
import com.service.backend.controller.entity.FitnessResponseEntity;
import com.service.backend.enums.StatusEnum;
import com.service.backend.mapper.FitnessMapper;
import com.service.backend.model.SignInReqDTO;
import com.service.backend.model.SignInResDTO;
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

        response.setBody(null);

        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<FitnessResponseEntity<SignInResDTO>> signIn(@Valid @RequestBody FitnessRequestEntity<SignInReqDTO> body) {

        var response = new FitnessResponseEntity<SignInResDTO>();
        var status = mapper.toStatusDTO(StatusEnum.SUCCESS);
        response.setStatus(status);
        response.setBody(new SignInResDTO("864e6697-e7a8-4363-b811-30d3ab560336"));

        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }
}
