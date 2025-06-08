package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.user.application.port.in.GetAllUsersUseCase;
import com.servinetcomputers.api.module.user.application.port.out.UserReadPort;
import com.servinetcomputers.api.module.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetAllUsersService implements GetAllUsersUseCase {
    private final UserReadPort readPort;

    @Override
    @Secured(value = ADMIN_AUTHORITY)
    public List<User> getAll() {
        return readPort.getAll();
    }
}
