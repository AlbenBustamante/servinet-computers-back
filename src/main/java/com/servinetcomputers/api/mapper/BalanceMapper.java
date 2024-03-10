package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.BalanceRequest;
import com.servinetcomputers.api.dto.response.BalanceResponse;
import com.servinetcomputers.api.model.Balance;
import com.servinetcomputers.api.util.formatter.ICurrencyFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    @Mapping(target = "platformName", source = "entity.platform.name")
    @Mapping(target = "initialBalance", expression = "java(formatter.format(entity.getInitialBalance()))")
    @Mapping(target = "finalBalance", expression = "java(formatter.format(entity.getFinalBalance()))")
    BalanceResponse toResponse(Balance entity, ICurrencyFormatter formatter);

    default List<BalanceResponse> toResponses(List<Balance> entities, ICurrencyFormatter formatter) {
        if (entities == null) {
            return null;
        }

        final List<BalanceResponse> balances = new ArrayList<>(entities.size());

        entities.forEach(entity -> balances.add(toResponse(entity, formatter)));

        return balances;
    }

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "platformId", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "campus", ignore = true)
    Balance toEntity(BalanceRequest request);

}
