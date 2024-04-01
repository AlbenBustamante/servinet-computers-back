package com.servinetcomputers.api.domain.balance.abs;

import com.servinetcomputers.api.domain.balance.model.Balance;
import com.servinetcomputers.api.domain.balance.model.dto.BalanceRequest;
import com.servinetcomputers.api.domain.balance.model.dto.BalanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    @Mapping(target = "platformName", source = "entity.platform.name")
    BalanceResponse toResponse(Balance entity);

    List<BalanceResponse> toResponses(List<Balance> entities);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "platformId", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Balance toEntity(BalanceRequest request);

}
