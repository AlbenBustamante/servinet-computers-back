package com.servinetcomputers.api.domain.transaction.abs;

import com.servinetcomputers.api.domain.transaction.dto.TransactionRequest;
import com.servinetcomputers.api.domain.transaction.dto.TransactionResponse;
import com.servinetcomputers.api.domain.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionResponse toResponse(Transaction entity);

    List<TransactionResponse> toResponses(List<Transaction> entities);

    @Mapping(target = "uses", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Transaction toEntity(TransactionRequest request);

}
