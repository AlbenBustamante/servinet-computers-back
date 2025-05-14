package com.servinetcomputers.api.module.passwordtempcode.persistence.mapper;

import com.servinetcomputers.api.module.passwordtempcode.domain.dto.CreatePasswordTempCodeDto;
import com.servinetcomputers.api.module.passwordtempcode.persistence.entity.PasswordTempCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PasswordTempCodeMapper {
    @Mapping(target = "usedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    PasswordTempCode toEntity(CreatePasswordTempCodeDto dto);
}
