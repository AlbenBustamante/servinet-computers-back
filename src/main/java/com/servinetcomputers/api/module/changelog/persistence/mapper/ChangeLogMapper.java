package com.servinetcomputers.api.module.changelog.persistence.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.changelog.persistence.entity.ChangeLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = CashRegisterDetailMapper.class)
public abstract class ChangeLogMapper {
    @Autowired
    protected ObjectMapper objectMapper;

    @Mapping(target = "cashRegisterDetailId", source = "cashRegisterDetail.id")
    public abstract ChangeLogResponse toResponse(ChangeLog entity);

    @Mapping(target = "previousData", expression = "java(objectMapper.writeValueAsString(dto.getPreviousData()))")
    @Mapping(target = "newData", expression = "java(objectMapper.writeValueAsString(dto.getNewData()))")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract ChangeLog toEntity(CreateChangeLogDto dto) throws JsonProcessingException;
}
