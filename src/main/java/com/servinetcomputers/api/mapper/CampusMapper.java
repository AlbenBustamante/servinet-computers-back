package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.model.Campus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

import static com.servinetcomputers.api.util.constants.DateTimeFormats.DATE_TIME_FORMAT;

/**
 * The campus' models mapper.
 */
@Mapper(componentModel = "spring", uses = PlatformMapper.class)
public interface CampusMapper {

    @Mapping(target = "createdAt", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "updatedAt", dateFormat = DATE_TIME_FORMAT)
    CampusResponse toResponse(Campus entity);

    List<CampusResponse> toResponses(Collection<Campus> entities);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "terminal", ignore = true)
    @Mapping(target = "platforms", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Campus toEntity(CampusRequest req);

}
