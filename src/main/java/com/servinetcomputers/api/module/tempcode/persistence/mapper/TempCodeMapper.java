package com.servinetcomputers.api.module.tempcode.persistence.mapper;

import com.servinetcomputers.api.module.tempcode.domain.dto.CreateTempCodeDto;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeDto;
import com.servinetcomputers.api.module.tempcode.persistence.entity.TempCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserPersistenceMapper.class)
public interface TempCodeMapper {
    TempCodeDto toDto(TempCode entity);

    @Mapping(target = "usedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TempCode toEntity(CreateTempCodeDto dto);

    TempCode toEntity(TempCodeDto dto);
}
