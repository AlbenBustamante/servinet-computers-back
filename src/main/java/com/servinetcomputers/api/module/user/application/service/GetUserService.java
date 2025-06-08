package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.user.application.port.in.GetUserUseCase;
import com.servinetcomputers.api.module.user.application.port.out.UserReadPort;
import com.servinetcomputers.api.module.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetUserService implements GetUserUseCase {
    private final UserReadPort readPort;

    @Override
    public User getById(Integer userId) {
        return readPort.get(userId);
    }
}
