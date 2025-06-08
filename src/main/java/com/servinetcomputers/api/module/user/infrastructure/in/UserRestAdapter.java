package com.servinetcomputers.api.module.user.infrastructure.in;

import com.servinetcomputers.api.core.common.RestAdapter;
import com.servinetcomputers.api.module.user.application.port.in.DeleteUserUseCase;
import com.servinetcomputers.api.module.user.application.port.in.GetAllUsersUseCase;
import com.servinetcomputers.api.module.user.application.port.in.GetUserUseCase;
import com.servinetcomputers.api.module.user.application.port.in.UpdateUserUseCase;
import com.servinetcomputers.api.module.user.application.port.in.command.UpdateUserCommand;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.UserDtoMapper;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestAdapter
@RequiredArgsConstructor
public class UserRestAdapter {
    private final GetUserUseCase getUserUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserDtoMapper mapper;

    public UserDto getById(Integer userId) {
        return mapper.toDto(getUserUseCase.getById(userId));
    }

    public List<UserDto> getAll() {
        return mapper.toDto(getAllUsersUseCase.getAll());
    }

    public UserDto update(Integer userId, UpdateUserCommand command) {
        return mapper.toDto(updateUserUseCase.update(userId, command));
    }

    public void delete(Integer userId) {
        deleteUserUseCase.delete(userId);
    }
}
