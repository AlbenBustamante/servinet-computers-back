package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformBalanceResponse;
import com.servinetcomputers.api.domain.platform.entity.PlatformBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlatformBalanceMapper {

    @Mapping(target = "platformName", source = "entity.platform.name")
    PlatformBalanceResponse toResponse(PlatformBalance entity);

    List<PlatformBalanceResponse> toResponses(List<PlatformBalance> entities);

    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    PlatformBalance toEntity(PlatformBalanceRequest request);

}
