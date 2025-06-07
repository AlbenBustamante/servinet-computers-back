package com.servinetcomputers.api.module.user.infrastructure.out.persistence;

import com.servinetcomputers.api.core.util.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Lógica de base de datos para la tabla de usuarios.
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    /**
     * Chequea si ya existe un usuario según un email y si no está eliminado.
     *
     * @param email el email a verificar.
     * @return {@code true} si se encuentra un usuario.
     */
    boolean existsByEmailAndEnabledTrue(String email);

    /**
     * Chequea si ya existe un usuario según un código y si no está eliminado.
     *
     * @param code el código a verificar.
     * @return {@code true} si se encuentra un usuario.
     */
    boolean existsByCodeAndEnabledTrue(String code);

    /**
     * Busca el {@code email} de un usuario según un código y si no está eliminado.
     *
     * @param code el código para hacer la búsqueda.
     * @return un {@link Optional} del {@code email} encontrado.
     */
    @Query("SELECT u.email FROM User u " +
            "WHERE u.code = :code " +
            "AND u.enabled = true")
    Optional<String> findEmailByCodeAndEnabledTrue(String code);

    /**
     * Busca un usuario según un código y si no está eliminado.
     *
     * @param code el código para hacer la búsqueda.
     * @return un {@link Optional} del usuario encontrado.
     */
    Optional<UserEntity> findByCodeAndEnabledTrue(String code);

    /**
     * Busca el último usuario registrado según un rol.
     *
     * @param role el {@link Role} para hacer la búsqueda.
     * @return un {@link Optional} del usuario encontrado.
     */
    Optional<UserEntity> findFirstByRoleOrderByCreatedDateDesc(Role role);

    /**
     * Busca un usuario según un ID y si no está eliminado.
     *
     * @param id el {@code ID} para hacer la búsqueda.
     * @return un {@link Optional} del usuario encontrado.
     */
    Optional<UserEntity> findByIdAndEnabledTrue(int id);
}
