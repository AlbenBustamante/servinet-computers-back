package com.servinetcomputers.api.domain.platform.persistence.mapper;

import com.servinetcomputers.api.domain.platform.domain.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.domain.platform.persistence.entity.Platform;
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

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Platform toEntity(PlatformRequest req);

    Platform toEntity(PlatformResponse response);
}
