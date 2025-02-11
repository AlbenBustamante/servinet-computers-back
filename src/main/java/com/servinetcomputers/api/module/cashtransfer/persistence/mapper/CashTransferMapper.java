package com.servinetcomputers.api.module.cashtransfer.persistence.mapper;

import com.servinetcomputers.api.core.security.service.UserLoggedService;
import com.servinetcomputers.api.module.cashregister.persistence.entity.CashRegisterDetail;
import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.persistence.entity.CashTransfer;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeDetail;
import com.servinetcomputers.api.module.safes.persistence.mapper.SafeDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CashRegisterDetailMapper.class, SafeDetailMapper.class})
public abstract class CashTransferMapper {
    @Autowired
    protected UserLoggedService userLoggedService;

    @Mapping(target = "receive", expression = "java(receive(entity))")
    @Mapping(target = "cashier", expression = "java(cashier(entity))")
    public abstract CashTransferDto toDto(CashTransfer entity);

    public abstract List<CashTransferDto> toDto(List<CashTransfer> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    public abstract CashTransfer toEntity(CreateCashTransferDto dto);

    protected String cashier(CashTransfer entity) {
        final var receive = receive(entity);

        if (receive) {
            return genericCashier(entity.getFromCashRegister(), entity.getFromSafe());
        }

        return genericCashier(entity.getToCashRegister(), entity.getToSafe());
    }

    protected boolean receive(CashTransfer entity) {
        return entity.getToCashRegister().getId() == userLoggedService.id();
    }

    private String genericCashier(CashRegisterDetail cashRegisterDetail, SafeDetail safeDetail) {
        if (cashRegisterDetail != null) {
            final var user = cashRegisterDetail.getUser();
            return user.getName() + " " + user.getLastName();
        }

        if (safeDetail != null) {
            final var safe = safeDetail.getSafe();
            return "Caja Fuerte N°" + safe.getNumeral();
        }

        return "-";
    }
}
