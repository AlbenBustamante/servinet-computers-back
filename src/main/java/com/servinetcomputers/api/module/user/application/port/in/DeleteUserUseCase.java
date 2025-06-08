package com.servinetcomputers.api.module.user.application.port.in;

/**
 * Caso de uso para eliminar a un usuario de forma segura.
 */
public interface DeleteUserUseCase {
    /**
     * Actualiza las propiedades de un usuario para ser eliminado de forma segura.
     *
     * @param userId el {@code ID} del usuario.
     */
    void delete(Integer userId);
}
