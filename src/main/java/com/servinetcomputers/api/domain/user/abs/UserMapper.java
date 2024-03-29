package com.servinetcomputers.api.domain.user.abs;

import com.servinetcomputers.api.domain.user.model.User;
import com.servinetcomputers.api.domain.user.model.dto.UserRequest;
import com.servinetcomputers.api.domain.user.model.dto.UserResponse;
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

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(UserRequest req);

}
