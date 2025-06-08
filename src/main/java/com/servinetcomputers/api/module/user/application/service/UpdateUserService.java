package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.user.application.port.in.UpdateUserUseCase;
import com.servinetcomputers.api.module.user.application.port.in.command.UpdateUserCommand;
import com.servinetcomputers.api.module.user.application.port.out.UserReadPort;
import com.servinetcomputers.api.module.user.application.port.out.UserWritePort;
import com.servinetcomputers.api.module.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UpdateUserService implements UpdateUserUseCase {
    private final UserWritePort writePort;
    private final UserReadPort readPort;

    @Override
    public User update(Integer id, UpdateUserCommand dto) {
        final var user = readPort.get(id)
                .copyWith(dto.name(), dto.lastName());

        return writePort.save(user);
    }
}
