package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.PlatformResponse;
import com.servinetcomputers.api.model.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The platform's models mapper.
 */
@Mapper(componentModel = "spring")
public interface PlatformMapper {

    PlatformResponse toResponse(Platform entity);

    List<PlatformResponse> toResponses(List<Platform> entities);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Platform toEntity(PlatformRequest req);

}
