package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.safes.dto.SafeDetailRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.entity.SafeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface SafeDetailMapper {

    @Mapping(target = "safeId", source = "safe.id")
    @Mapping(target = "detailInitialBase", source = "initialBase")
    @Mapping(target = "detailFinalBase", source = "finalBase")
    SafeDetailResponse toResponse(SafeDetail entity);

    List<SafeDetailResponse> toResponses(List<SafeDetail> entities);

    @Mapping(target = "safe", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "calculatedBase", ignore = true)
    SafeDetail toEntity(SafeDetailRequest request);

}
