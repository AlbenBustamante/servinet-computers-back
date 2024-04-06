package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.entity.CashRegister;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashRegisterMapper {

    CashRegisterResponse toResponse(CashRegister entity);

    List<CashRegisterResponse> toResponses(List<CashRegister> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    CashRegister toEntity(CashRegisterRequest request);

}
