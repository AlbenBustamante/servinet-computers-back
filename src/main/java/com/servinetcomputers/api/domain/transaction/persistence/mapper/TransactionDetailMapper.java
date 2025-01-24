package com.servinetcomputers.api.domain.transaction.persistence.mapper;

import com.servinetcomputers.api.domain.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.domain.transaction.persistence.entity.TransactionDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class, CashRegisterDetailMapper.class})
public interface TransactionDetailMapper {
    @Mapping(target = "description", source = "transaction.description")
    @Mapping(target = "cashRegisterDetailId", source = "cashRegisterDetail.id")
    TransactionDetailResponse toResponse(TransactionDetail entity);

    List<TransactionDetailResponse> toResponses(List<TransactionDetail> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TransactionDetail toEntity(TransactionDetailRequest request);
}
