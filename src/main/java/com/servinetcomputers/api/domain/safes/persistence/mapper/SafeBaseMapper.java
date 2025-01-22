package com.servinetcomputers.api.domain.safes.persistence.mapper;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseResponse;
import com.servinetcomputers.api.domain.safes.persistence.entity.SafeBase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface SafeBaseMapper {

    @Mapping(target = "safeDetailId", source = "safeDetail.id")
    @Mapping(target = "detailBase", source = "base")
    SafeBaseResponse toResponse(SafeBase entity);

    List<SafeBaseResponse> toResponses(List<SafeBase> entities);

    @Mapping(target = "safeDetail", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    SafeBase toEntity(SafeBaseRequest request);

}
