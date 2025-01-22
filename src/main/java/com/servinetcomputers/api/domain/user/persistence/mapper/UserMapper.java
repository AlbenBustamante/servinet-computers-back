package com.servinetcomputers.api.domain.user.persistence.mapper;

import com.servinetcomputers.api.domain.user.domain.dto.UserRequest;
import com.servinetcomputers.api.domain.user.domain.dto.UserResponse;
import com.servinetcomputers.api.domain.user.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The user's models mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User entity);

    List<UserResponse> toResponses(List<User> entities);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    User toEntity(UserRequest req);

}
