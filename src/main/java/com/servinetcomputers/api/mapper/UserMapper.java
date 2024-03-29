package com.servinetcomputers.api.mapper;

import com.servinetcomputers.api.dto.request.UserRequest;
import com.servinetcomputers.api.dto.response.UserResponse;
import com.servinetcomputers.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static com.servinetcomputers.api.util.constants.DateTimeFormats.DATE_TIME_FORMAT;

/**
 * The user's models mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "createdAt", dateFormat = DATE_TIME_FORMAT)
    @Mapping(target = "updatedAt", dateFormat = DATE_TIME_FORMAT)
    UserResponse toResponse(User entity);

    List<UserResponse> toResponses(List<User> entities);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(UserRequest req);

}
