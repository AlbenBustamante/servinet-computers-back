package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.model.Campus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The campus' models mapper.
 */
@Mapper(componentModel = "spring")
public interface CampusMapper {

    CampusResponse toResponse(Campus entity);

    List<CampusResponse> toResponses(List<Campus> entities);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "terminal", ignore = true)
    @Mapping(target = "platforms", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Campus toEntity(CampusRequest req);

}
