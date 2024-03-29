package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.model.Platform;
import com.servinetcomputers.api.domain.platform.model.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.model.dto.PlatformResponse;
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

    @Mapping(target = "transfers", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Platform toEntity(PlatformRequest req);

}
