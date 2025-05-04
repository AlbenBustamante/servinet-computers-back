package com.servinetcomputers.api.module.safes.persistence.mapper;

import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SafeMapper.class, BaseMapper.class})
public interface SafeDetailMapper {
    @Mapping(target = "safeId", source = "safe.id")
    @Mapping(target = "detailInitialBase", source = "initialBase")
    @Mapping(target = "detailFinalBase", source = "finalBase")
    @Mapping(target = "initialBase", expression = "java(safeDetailDto.getDetailInitialBase() != null ? safeDetailDto.getDetailInitialBase().calculate() : null)")
    @Mapping(target = "finalBase", expression = "java(safeDetailDto.getDetailFinalBase() != null ? safeDetailDto.getDetailFinalBase().calculate() : null)")
    SafeDetailDto toDto(SafeDetail entity);

    List<SafeDetailDto> toDto(List<SafeDetail> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "calculatedBase", ignore = true)
    SafeDetail toEntity(CreateSafeDetailDto dto);

    @Mapping(target = "initialBase", source = "detailInitialBase")
    @Mapping(target = "finalBase", source = "detailFinalBase")
    SafeDetail toEntity(SafeDetailDto dto);
}
