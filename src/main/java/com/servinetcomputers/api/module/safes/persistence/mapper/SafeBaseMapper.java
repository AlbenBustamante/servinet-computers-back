package com.servinetcomputers.api.module.safes.persistence.mapper;

import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeBaseDto;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeBase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface SafeBaseMapper {
    @Mapping(target = "safeDetailId", source = "safeDetail.id")
    @Mapping(target = "detailBase", source = "base")
    @Mapping(target = "base", expression = "java(safeBaseDto.getDetailBase() != null ? safeBaseDto.getDetailBase().calculate() : null)")
    SafeBaseDto toDto(SafeBase entity);

    List<SafeBaseDto> toDto(List<SafeBase> entities);

    @Mapping(target = "base", expression = "java(baseMapper.toStr(dto.getBaseDto()))")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    SafeBase toEntity(CreateSafeBaseDto dto);

    @Mapping(target = "safeDetail.id", source = "safeDetailId")
    @Mapping(target = "base", source = "detailBase")
    SafeBase toEntity(SafeBaseDto dto);
}
