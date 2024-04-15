package com.servinetcomputers.api.domain.transfer.abs;

import com.servinetcomputers.api.domain.transfer.Transfer;
import com.servinetcomputers.api.domain.transfer.dto.TransferRequest;
import com.servinetcomputers.api.domain.transfer.dto.TransferResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The transfer's models mapper.
 */
@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(target = "platformName", source = "entity.platform.name")
    TransferResponse toResponse(Transfer entity);

    List<TransferResponse> toResponses(List<Transfer> entities);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "platformId", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Transfer toEntity(TransferRequest req);

}
