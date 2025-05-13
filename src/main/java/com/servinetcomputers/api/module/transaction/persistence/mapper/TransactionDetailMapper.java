package com.servinetcomputers.api.module.transaction.persistence.mapper;

import com.servinetcomputers.api.module.cashregister.persistence.mapper.CashRegisterDetailMapper;
import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import com.servinetcomputers.api.module.transaction.persistence.entity.TransactionDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class, CashRegisterDetailMapper.class})
public interface TransactionDetailMapper {
    @Mapping(target = "description", source = "transaction.description")
    @Mapping(target = "cashRegisterDetailId", source = "cashRegisterDetail.id")
    TransactionDetailDto toDto(TransactionDetail entity);

    List<TransactionDetailDto> toDto(List<TransactionDetail> entities);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TransactionDetail toEntity(CreateTransactionDetailDto dto);

    TransactionDetail toEntity(TransactionDetailDto dto);
}
