package com.servinetcomputers.api.module.user.application.port.in;

import com.servinetcomputers.api.module.user.application.port.in.command.UpdateUserCommand;
import com.servinetcomputers.api.module.user.domain.User;

/**
 * Caso de uso para actualizar la información básica de un usuario.
 */
public interface UpdateUserUseCase {
    /**
     * Actualiza la información básica de un usuario.
     *
     * @param userId  {@code ID} del usuario.
     * @param command {@link UpdateUserCommand} con la data.
     * @return {@link User} actualizado.
     */
    User update(Integer userId, UpdateUserCommand command);
}
