package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.Platform;
import com.servinetcomputers.api.domain.platform.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformResponse;
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
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    Platform toEntity(PlatformRequest req);

}
