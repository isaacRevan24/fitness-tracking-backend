package com.service.backend.mapper;

import com.service.backend.model.AddGoalsReqDTO;
import com.service.backend.model.AddGoalsResDTO;
import com.service.backend.model.SignUpReqDTO;
import com.service.backend.model.StatusDTO;
import com.service.backend.enums.StatusEnum;
import com.service.backend.repository.entities.ClientEntity;
import com.service.backend.repository.entities.ClientValuesEntity;
import com.service.backend.repository.entities.GoalsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

/**
 * @author Severiano Atencio
 */
@Mapper(componentModel = "spring")
public interface FitnessMapper {

    @Mapping(source = "status.code", target = "code")
    @Mapping(source = "status.message", target = "message")
    @Mapping(source = "status.status", target = "httpStatus")
    StatusDTO toStatusDTO(final StatusEnum status);

    @Mapping(source = "request.username", target = "username")
    @Mapping(source = "request.password", target = "password")
    @Mapping(source = "request.age", target = "age")
    ClientEntity toClientEntity(final SignUpReqDTO request);


    @Mapping(source = "request.height", target = "height")
    @Mapping(source = "request.weight", target = "weight")
    @Mapping(source = "request.heightMetric", target = "heightMetric")
    @Mapping(source = "request.weightMetric", target = "weightMetric")
    @Mapping(source = "id", target = "id")
    ClientValuesEntity toClientValuesEntity(final SignUpReqDTO request, final UUID id);

    @Mapping(source = "request.clientId", target = "clientId")
    @Mapping(source = "request.weightGoal", target = "weightGoal")
    @Mapping(source = "request.stepsGoal", target = "stepsGoal")
    GoalsEntity toGoalsEntity(final AddGoalsReqDTO reqDTO);

    @Mapping(source = "goal.stepsGoal", target = "stepsGoal")
    @Mapping(source = "goal.steps", target = "steps")
    AddGoalsResDTO toAddGoalResp(final GoalsEntity goal);

}
