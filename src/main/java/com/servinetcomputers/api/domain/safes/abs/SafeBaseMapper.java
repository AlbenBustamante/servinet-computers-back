package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseResponse;
import com.servinetcomputers.api.domain.safes.entity.SafeBase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface SafeBaseMapper {

    @Mapping(target = "safeId", ignore = true)
    SafeBaseResponse toResponse(SafeBase entity);

    List<SafeBaseResponse> toResponses(List<SafeBase> entities);

    @Mapping(target = "safe", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    SafeBase toEntity(SafeBaseRequest request);

}
