package com.servinetcomputers.api.module.user.application.service;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.user.application.port.in.DeleteUserUseCase;
import com.servinetcomputers.api.module.user.application.port.out.UserReadPort;
import com.servinetcomputers.api.module.user.application.port.out.UserWritePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DeleteUserService implements DeleteUserUseCase {
    private final UserWritePort writePort;
    private final UserReadPort readPort;

    @Override
    @Secured(value = ADMIN_AUTHORITY)
    public void delete(Integer userId) {
        final var user = readPort.get(userId).delete();
        writePort.save(user);
    }
}
