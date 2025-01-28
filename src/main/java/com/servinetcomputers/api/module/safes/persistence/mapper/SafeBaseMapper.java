package com.servinetcomputers.api.module.safes.persistence.mapper;

import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeBaseResponse;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeBase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class, SafeDetailMapper.class})
public interface SafeBaseMapper {
    @Mapping(target = "safeDetailId", source = "safeDetail.id")
    @Mapping(target = "detailBase", source = "base")
    SafeBaseResponse toResponse(SafeBase entity);

    List<SafeBaseResponse> toResponses(List<SafeBase> entities);

    @Mapping(target = "base", expression = "java(baseMapper.toStr(request.getBaseDto()))")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    SafeBase toEntity(SafeBaseRequest request);
}
