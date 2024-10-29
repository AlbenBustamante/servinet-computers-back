package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;
import com.servinetcomputers.api.domain.safes.entity.Safe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SafeMapper {

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
