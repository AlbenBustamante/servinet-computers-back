package com.servinetcomputers.api.module.user.application.port.out;

import com.servinetcomputers.api.module.user.domain.User;

/**
 * Puertos de escritura y salida para usuarios.
 */
public interface UserWritePort {
    /**
     * Guarda un nuevo registro de usuario.
     *
     * @param user el usuario a persistir.
     * @return un {@link User} desde la base de datos.
     */
    User save(User user);

    /**
     * Busca un usuario según su código y luego actualiza su contraseña.
     *
     * @param userCode el código de usuario a buscar.
     * @param password la contraseña a encriptar y guardar.
     */
    void savePassword(String userCode, String password);
}
