package com.servinetcomputers.api.module.safes.persistence.mapper;

import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDto;
import com.servinetcomputers.api.module.safes.persistence.entity.Safe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface SafeMapper {
    SafeDto toDto(Safe entity);

    List<SafeDto> toDto(List<Safe> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Safe toEntity(CreateSafeDto dto);

    Safe toEntity(SafeDto dto);
}
