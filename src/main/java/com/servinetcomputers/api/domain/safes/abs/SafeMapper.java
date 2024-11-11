package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.safes.Safe;
import com.servinetcomputers.api.domain.safes.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface SafeMapper {

    @Mapping(target = "detailInitialBase", source = "initialBase")
    @Mapping(target = "detailFinalBase", source = "finalBase")
    SafeResponse toResponse(Safe entity);

    List<SafeResponse> toResponses(List<Safe> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Safe toEntity(SafeRequest request);

}
