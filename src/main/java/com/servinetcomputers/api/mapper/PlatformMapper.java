package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.PlatformResponse;
import com.servinetcomputers.api.model.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static com.servinetcomputers.api.util.constants.DateTimeFormats.DATE_TIME_FORMAT;

/**
 * The platform's models mapper.
 */
@Mapper(componentModel = "spring")
public interface PlatformMapper {
    
    @Mapping(target = "createdAt", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "updatedAt", dateFormat = DATE_TIME_FORMAT)
    PlatformResponse toResponse(Platform entity);

    List<PlatformResponse> toResponses(List<Platform> entities);

    @Mapping(target = "transfers", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Platform toEntity(PlatformRequest req);

}
