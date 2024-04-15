package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseResponse;
import com.servinetcomputers.api.domain.cashregister.entity.CashRegisterBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface CashRegisterBaseMapper {


    CashRegisterBaseResponse toResponse(CashRegisterBalance entity);

    List<CashRegisterBaseResponse> toResponses(List<CashRegisterBalance> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "cashRegisterDetail", ignore = true)
    CashRegisterBalance toEntity(CashRegisterBaseRequest request);

}
