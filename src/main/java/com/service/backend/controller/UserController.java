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

    @Autowired
    @Qualifier("SignInCommand")
    private FitnessCommand<SignInReqDTO, SignInResDTO> signInCommand;

    @GetMapping(path = "/health-check")
    public ResponseEntity<BaseResponseEntity> healthCheck() {

        final var status = mapper.toStatusDTO(StatusEnum.SUCCESS);
        final var response = new BaseResponseEntity(status);

        return ResponseEntity.status(status.getHttpStatus()).body(response);
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<BaseResponseEntity> signUp(@Valid @RequestBody FitnessRequestEntity<SignUpReqDTO> body) {

        final var request = new FitnessRequestEntity<SignUpReqDTO>();

        request.setBody(body.getBody());

        final var response = signUpCommand.execute(request);

        response.setBody(null);

        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }

    @PostMapping(path = "/sign-in")
    public ResponseEntity<FitnessResponseEntity<SignInResDTO>> signIn(@Valid @RequestBody FitnessRequestEntity<SignInReqDTO> body) {

        final var request = new FitnessRequestEntity<SignInReqDTO>();

        request.setBody(body.getBody());

        final var response = signInCommand.execute(request);

        return ResponseEntity.status(response.getStatus().getHttpStatus()).body(response);
    }
}
