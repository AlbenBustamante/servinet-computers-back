package com.servinetcomputers.api.module.tempcode.persistence.mapper;

import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;
import com.servinetcomputers.api.module.tempcode.persistence.entity.TempCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TempCodeMapper {
    @Mapping(target = "usedBy", source = "usedBy.id")
    TempCodeResponse toResponse(TempCode entity);
}
