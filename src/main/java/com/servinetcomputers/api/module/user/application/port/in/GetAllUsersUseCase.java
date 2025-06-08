package com.servinetcomputers.api.module.user.application.port.in;

import com.servinetcomputers.api.module.user.domain.User;

import java.util.List;

/**
 * Caso de uso para obtener a todos los usuarios.
 */
public interface GetAllUsersUseCase {
    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return la lista de usuarios encontrados.
     */
    List<User> getAll();
}
