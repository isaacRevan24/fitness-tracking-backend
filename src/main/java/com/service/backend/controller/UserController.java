package com.service.backend.controller;

import com.service.backend.controller.dto.StatusDTO;
import com.service.backend.controller.entity.BaseResponseEntity;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/health-check")
    public ResponseEntity<BaseResponseEntity> healthCheck() {

        var status = new StatusDTO("fit-00", "success", HttpStatus.OK);
        var response = new BaseResponseEntity();
        response.setStatus(status);

        return ResponseEntity.status(status.getHttpStatus()).body(response);

    }
}
