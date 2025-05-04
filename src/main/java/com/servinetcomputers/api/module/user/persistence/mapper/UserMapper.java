package com.servinetcomputers.api.module.user.persistence.mapper;

import com.servinetcomputers.api.module.user.domain.dto.CreateUserDto;
import com.servinetcomputers.api.module.user.domain.dto.UserDto;
import com.servinetcomputers.api.module.user.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The user's models mapper.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);

    List<UserDto> toDto(List<User> entities);

    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    User toEntity(CreateUserDto dto);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto dto);
}
