package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.BalanceRequest;
import com.servinetcomputers.api.dto.response.BalanceResponse;
import com.servinetcomputers.api.model.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    @Mapping(target = "platformName", source = "platform.name")
    BalanceResponse toResponse(Balance entity);

    List<BalanceResponse> toResponses(List<Balance> entities);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "platformId", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "campus", ignore = true)
    Balance toEntity(BalanceRequest request);

}
