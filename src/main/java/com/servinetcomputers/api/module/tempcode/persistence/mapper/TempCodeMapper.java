package com.servinetcomputers.api.module.tempcode.persistence.mapper;

import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeRequest;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;
import com.servinetcomputers.api.module.tempcode.persistence.entity.TempCode;
import com.servinetcomputers.api.module.user.persistence.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TempCodeMapper {
    TempCodeResponse toResponse(TempCode entity);

    @Mapping(target = "usedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TempCode toEntity(TempCodeRequest request);

    TempCode toEntity(TempCodeResponse response);
}
