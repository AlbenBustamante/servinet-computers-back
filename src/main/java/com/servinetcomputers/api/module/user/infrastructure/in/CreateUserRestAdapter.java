package com.servinetcomputers.api.module.user.infrastructure.in;

import com.servinetcomputers.api.core.common.RestAdapter;
import com.servinetcomputers.api.module.user.application.port.in.CreateUserUseCase;
import com.servinetcomputers.api.module.user.application.port.in.command.CreateUserCommand;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.UserDtoMapper;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserDto;
import lombok.RequiredArgsConstructor;

@RestAdapter
@RequiredArgsConstructor
public class CreateUserRestAdapter {
    private final CreateUserUseCase createUserUseCase;
    private final UserDtoMapper mapper;

    public UserDto create(CreateUserCommand command) {
        return mapper.toDto(createUserUseCase.create(command));
    }
}
