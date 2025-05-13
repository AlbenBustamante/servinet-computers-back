package com.servinetcomputers.api.module.transaction.persistence.mapper;

import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDto;
import com.servinetcomputers.api.module.transaction.persistence.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDto(Transaction entity);

    List<TransactionDto> toDto(List<Transaction> entities);

    @Mapping(target = "uses", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Transaction toEntity(CreateTransactionDto dto);

    Transaction toEntity(TransactionDto dto);
}
