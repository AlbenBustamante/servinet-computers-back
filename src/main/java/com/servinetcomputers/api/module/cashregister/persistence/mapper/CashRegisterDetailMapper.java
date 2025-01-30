package com.servinetcomputers.api.module.cashregister.persistence.mapper;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import com.servinetcomputers.api.module.user.persistence.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CashRegisterMapper.class, UserMapper.class, BaseMapper.class}, imports = LocalTime.class)
public abstract class CashRegisterDetailMapper {
    @Autowired
    protected DateTimeService dateTimeService;

    @Mapping(target = "initialWorking", expression = "java(entity.getWorkingHours()[0] != null ? dateTimeService.currentOf(entity.getWorkingHours()[0]) : null)")
    @Mapping(target = "initialBreak", expression = "java(entity.getWorkingHours()[1] != null ? dateTimeService.currentOf(entity.getWorkingHours()[1]) : null)")
    @Mapping(target = "finalBreak", expression = "java(entity.getWorkingHours()[2] != null ? dateTimeService.currentOf(entity.getWorkingHours()[2]) : null)")
    @Mapping(target = "finalWorking", expression = "java(entity.getWorkingHours()[3] != null ? dateTimeService.currentOf(entity.getWorkingHours()[3]) : null)")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "detailFinalBase", source = "finalBase")
    @Mapping(target = "detailInitialBase", source = "initialBase")
    public abstract CashRegisterDetailResponse toResponse(CashRegisterDetail entity);

    public abstract List<CashRegisterDetailResponse> toResponses(List<CashRegisterDetail> entities);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "workingHours", expression = "java(new LocalTime[]{request.getInitialWorking(), null, null, null})")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract CashRegisterDetail toEntity(CreateCashRegisterDetailDto request);

    @Mapping(target = "workingHours", expression = "java(new LocalTime[]{dateTimeService.timeOf(response.getInitialWorking()), dateTimeService.timeOf(response.getInitialBreak()), dateTimeService.timeOf(response.getFinalBreak()), dateTimeService.timeOf(response.getFinalWorking())})")
    @Mapping(target = "initialBase", source = "detailInitialBase")
    @Mapping(target = "finalBase", source = "detailFinalBase")
    public abstract CashRegisterDetail toEntity(CashRegisterDetailResponse response);
}
