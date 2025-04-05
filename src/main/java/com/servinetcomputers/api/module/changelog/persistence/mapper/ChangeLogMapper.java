package com.servinetcomputers.api.module.changelog.persistence.mapper;

import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.changelog.persistence.entity.ChangeLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CashRegisterDetailMapper.class)
public interface ChangeLogMapper {
    @Mapping(target = "cashRegisterDetailId", source = "cashRegisterDetail.id")
    ChangeLogResponse toResponse(ChangeLog entity);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    ChangeLog toEntity(CreateChangeLogDto dto);
}
