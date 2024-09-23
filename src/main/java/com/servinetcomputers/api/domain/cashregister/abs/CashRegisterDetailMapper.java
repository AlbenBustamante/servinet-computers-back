package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CashRegisterMapper.class, BaseMapper.class})
public interface CashRegisterDetailMapper {

    CashRegisterDetailResponse toResponse(CashRegisterDetail entity);

    List<CashRegisterDetailResponse> toResponses(List<CashRegisterDetail> entities);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "cashRegister", ignore = true)
    CashRegisterDetail toEntity(CashRegisterDetailRequest request);

}
