package com.servinetcomputers.api.module.user.application.port.in;

import com.servinetcomputers.api.module.user.application.port.in.command.CreateUserCommand;
import com.servinetcomputers.api.module.user.domain.User;

/**
 * Caso de uso para crear y registrar un nuevo usuario.
 */
public interface CreateUserUseCase {
    /**
     * Crea y persiste un nuevo usuario.
     *
     * @param command {@link CreateUserCommand} con la data a persistir.
     * @return el {@link User} registrado.
     */
    User create(CreateUserCommand command);
}
