package com.servinetcomputers.api.module.cashregister.persistence.mapper;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.base.BaseMapper;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetailEntity;
import com.servinetcomputers.api.module.user.infrastructure.out.persistence.UserPersistenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CashRegisterMapper.class, UserPersistenceMapper.class, BaseMapper.class}, imports = LocalTime.class)
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
    @Mapping(target = "initialBase", expression = "java(cashRegisterDetailDto.getDetailInitialBase() != null ? cashRegisterDetailDto.getDetailInitialBase().calculate() : null)")
    @Mapping(target = "finalBase", expression = "java(cashRegisterDetailDto.getDetailFinalBase() != null ? cashRegisterDetailDto.getDetailFinalBase().calculate() : null)")
    public abstract CashRegisterDetailDto toDto(CashRegisterDetailEntity entity);

    public abstract List<CashRegisterDetailDto> toDto(List<CashRegisterDetailEntity> entities);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "workingHours", expression = "java(new LocalTime[]{dto.getInitialWorking(), null, null, null})")
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract CashRegisterDetailEntity toEntity(CreateCashRegisterDetailDto dto);

    @Mapping(target = "workingHours", expression = "java(new LocalTime[]{dateTimeService.timeOf(dto.getInitialWorking()), dateTimeService.timeOf(dto.getInitialBreak()), dateTimeService.timeOf(dto.getFinalBreak()), dateTimeService.timeOf(dto.getFinalWorking())})")
    @Mapping(target = "initialBase", source = "detailInitialBase")
    @Mapping(target = "finalBase", source = "detailFinalBase")
    public abstract CashRegisterDetailEntity toEntity(CashRegisterDetailDto dto);
}
