package com.servinetcomputers.api.domain.cashregister.persistence.mapper;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CreateCashRegisterDetailDto;
import com.servinetcomputers.api.domain.cashregister.persistence.entity.CashRegisterDetail;
import com.servinetcomputers.api.domain.user.persistence.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CashRegisterMapper.class, UserMapper.class, BaseMapper.class}, imports = LocalTime.class)
public interface CashRegisterDetailMapper {
    @Mapping(target = "initialWorking", expression = "java(entity.getWorkingHours()[0])")
    @Mapping(target = "initialBreak", expression = "java(entity.getWorkingHours()[1])")
    @Mapping(target = "finalWorking", expression = "java(entity.getWorkingHours()[2])")
    @Mapping(target = "finalBreak", expression = "java(entity.getWorkingHours()[3])")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "detailFinalBase", source = "finalBase")
    @Mapping(target = "detailInitialBase", source = "initialBase")
    CashRegisterDetailResponse toResponse(CashRegisterDetail entity);

    List<CashRegisterDetailResponse> toResponses(List<CashRegisterDetail> entities);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "workingHours", expression = "java(new LocalTime[]{request.getInitialWorking(), null, null, null})")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    CashRegisterDetail toEntity(CreateCashRegisterDetailDto request);

    @Mapping(target = "initialBase", source = "detailInitialBase")
    @Mapping(target = "finalBase", source = "detailFinalBase")
    @Mapping(target = "workingHours", ignore = true)
    @Mapping(target = "user", ignore = true)
    CashRegisterDetail toEntity(CashRegisterDetailResponse response);
}
