package com.servinetcomputers.api.module.platform.persistence.mapper;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;
import com.servinetcomputers.api.module.platform.persistence.entity.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The platform's models mapper.
 */
@Mapper(componentModel = "spring")
public interface PlatformMapper {
    PlatformDto toDto(Platform entity);

    List<PlatformDto> toDto(List<Platform> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Platform toEntity(CreatePlatformDto dto);

    Platform toEntity(PlatformDto dto);
}
