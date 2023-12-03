package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.TransferRequest;
import com.servinetcomputers.api.dto.response.TransferResponse;
import com.servinetcomputers.api.exception.TransferUnavailableException;
import com.servinetcomputers.api.model.Transfer;
import com.servinetcomputers.api.util.ICurrencyFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

/**
 * The transfer's models mapper.
 */
@Mapper(componentModel = "spring")
public interface TransferMapper {

    @Mapping(target = "platformName", source = "entity.platform.name")
    @Mapping(target = "value", expression = "java(currencyFormatter.format(entity.getValue()))")
    TransferResponse toResponse(Transfer entity, ICurrencyFormatter currencyFormatter);

    default List<TransferResponse> toResponses(List<Transfer> entities, ICurrencyFormatter currencyFormatter) {
        if (entities == null || currencyFormatter == null) {
            throw new TransferUnavailableException(2);
        }

        final List<TransferResponse> responses = new ArrayList<>(entities.size());

        entities.forEach(entity -> responses.add(toResponse(entity, currencyFormatter)));

        return responses;
    }

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "platformId", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "campus", ignore = true)
    Transfer toEntity(TransferRequest req);

}
