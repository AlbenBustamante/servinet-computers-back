package com.servinetcomputers.api.module.platform.persistence.mapper;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformBalanceDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformBalanceDto;
import com.servinetcomputers.api.module.platform.persistence.entity.PlatformBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PlatformMapper.class)
public interface PlatformBalanceMapper {
    PlatformBalanceDto toResponse(PlatformBalance entity);

    List<PlatformBalanceDto> toResponses(List<PlatformBalance> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    PlatformBalance toEntity(CreatePlatformBalanceDto dto);

    PlatformBalance toEntity(PlatformBalanceDto response);
}
