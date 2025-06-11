package com.servinetcomputers.api.module.user.application.port.in;

import com.servinetcomputers.api.module.user.domain.User;

/**
 * Caso de uso para obtener un usuario.
 */
public interface GetUserUseCase {
    /**
     * Obtiene un usuario según un {@code ID}.
     *
     * @param userId el {@code ID} para hacer la búsqueda.
     * @return un {@link User}.
     */
    User getById(Integer userId);
}
