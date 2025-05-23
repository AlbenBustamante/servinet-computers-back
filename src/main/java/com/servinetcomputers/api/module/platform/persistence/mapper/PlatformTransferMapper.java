package com.servinetcomputers.api.module.platform.persistence.mapper;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferWithVouchersDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.platform.persistence.entity.PlatformTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The transfer's models mapper.
 */
@Mapper(componentModel = "spring", uses = {PlatformMapper.class})
public interface PlatformTransferMapper {
    PlatformTransferDto toDto(PlatformTransfer entity);

    List<PlatformTransferDto> toDto(List<PlatformTransfer> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    PlatformTransfer toEntity(CreatePlatformTransferWithVouchersDto dto);

    PlatformTransfer toEntity(PlatformTransferDto dto);
}
