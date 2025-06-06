package com.servinetcomputers.api.module.user.application.port.out;

import com.servinetcomputers.api.core.util.enums.Role;
import com.servinetcomputers.api.module.user.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Puertos de lectura y salida para usuarios.
 */
public interface UserReadPort {
    /**
     * Chequea si ya existe un usuario según un email.
     *
     * @param email el email para chequear.
     * @return {@code true} si se encuentra un registro.
     */
    boolean existsByEmail(String email);

    /**
     * Chequea si ya existe un usuario según un código de usuario.
     *
     * @param code el código de usuario para chequear.
     * @return {@code true} si se encuentra un registro.
     */
    boolean existsByCode(String code);

    /**
     * Obtiene un {@code email} según un código de usuario.
     *
     * @param code el código de usuario para hacer la búsqueda.
     * @return el email contrado.
     */
    String getEmailByCode(String code);

    /**
     * Obtiene el último registro de un {@link User} según un rol específico.
     *
     * @param role el {@link Role} para hacer la búsqueda.
     * @return un {@link Optional} del usuario encontrado.
     */
    Optional<User> getLastByRole(Role role);

    /**
     * Obtiene un {@link User} según un {@code ID}.
     *
     * @param userId el {@code ID} para hacer la búsqueda.
     * @return el {@link User} encontrado.
     */
    User get(int userId);

    /**
     * Obtiene todos los registros de usuarios.
     *
     * @return una lista con los registros encontrados.
     */
    List<User> getAll();
}
