package com.service.backend.mapper;

import com.service.backend.controller.dto.StatusDTO;
import com.service.backend.enums.StatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Severiano Atencio
 */
@Mapper(componentModel = "spring")
public interface FitnessMapper {

    @Mapping(source = "code", target = "code")
    @Mapping(source = "message", target = "message")
    @Mapping(source = "status", target = "httpStatus")
    StatusDTO toStatusDTO(final StatusEnum status);

}
