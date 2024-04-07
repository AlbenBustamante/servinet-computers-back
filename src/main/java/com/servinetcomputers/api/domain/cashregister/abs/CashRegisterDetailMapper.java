package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailReq;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRes;
import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashRegisterDetailMapper {

    CashRegisterDetailRes toResponse(CashRegisterDetail entity);

    List<CashRegisterDetailRes> toResponses(List<CashRegisterDetail> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "cashRegister", ignore = true)
    CashRegisterDetail toEntity(CashRegisterDetailReq request);

}
