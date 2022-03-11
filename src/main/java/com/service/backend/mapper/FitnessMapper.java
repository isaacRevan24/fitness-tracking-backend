package com.service.backend.mapper;

import com.service.backend.model.SignUpReqDTO;
import com.service.backend.model.StatusDTO;
import com.service.backend.enums.StatusEnum;
import com.service.backend.repository.entities.ClientEntity;
import com.service.backend.repository.entities.ClientValuesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    ClientValuesEntity toClientValuesEntity(final SignUpReqDTO request);

}
