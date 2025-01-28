package com.servinetcomputers.api.module.safes.persistence.mapper;

import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SafeMapper.class, BaseMapper.class})
public interface SafeDetailMapper {
    @Mapping(target = "safeId", source = "safe.id")
    @Mapping(target = "detailInitialBase", source = "initialBase")
    @Mapping(target = "detailFinalBase", source = "finalBase")
    SafeDetailResponse toResponse(SafeDetail entity);

    List<SafeDetailResponse> toResponses(List<SafeDetail> entities);

    @Mapping(target = "initialBase", expression = "java(baseMapper.toStr(request.getInitialBase()))")
    @Mapping(target = "finalBase", expression = "java(baseMapper.toStr(request.getFinalBase()))")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "calculatedBase", ignore = true)
    SafeDetail toEntity(SafeDetailRequest request);

    @Mapping(target = "initialBase", expression = "java(baseMapper.toStr(response.getDetailInitialBase()))")
    @Mapping(target = "finalBase", expression = "java(baseMapper.toStr(response.getDetailFinalBase()))")
    SafeDetail toEntity(SafeDetailResponse response);
}
