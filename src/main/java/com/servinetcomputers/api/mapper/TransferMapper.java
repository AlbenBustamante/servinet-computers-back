package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.TransferRequest;
import com.servinetcomputers.api.dto.response.TransferResponse;
import com.servinetcomputers.api.model.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The transfer's models mapper.
 */
@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(target = "platformName", source = "platform.name")
    TransferResponse toResponse(Transfer entity);

    List<TransferResponse> toResponses(List<Transfer> entities);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "platformId", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "campus", ignore = true)
    Transfer toEntity(TransferRequest req);

}
