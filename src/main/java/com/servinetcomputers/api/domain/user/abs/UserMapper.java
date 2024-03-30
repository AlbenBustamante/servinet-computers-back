package com.servinetcomputers.api.domain.user.abs;

import com.servinetcomputers.api.domain.user.model.User;
import com.servinetcomputers.api.domain.user.model.dto.UserRequest;
import com.servinetcomputers.api.domain.user.model.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The user's models mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User entity);

    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    User toEntity(UserRequest req);

}
