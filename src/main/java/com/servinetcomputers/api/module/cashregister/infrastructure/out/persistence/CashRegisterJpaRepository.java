package com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence;

import com.servinetcomputers.api.module.cashregister.infrastructure.out.persistence.entity.CashRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Lógica de base de datos para las cajas registradoras.
 */
public interface CashRegisterJpaRepository extends JpaRepository<CashRegisterEntity, Integer> {
    /**
     * Encuentra una caja registradora no eliminada según un ID.
     *
     * @param id ID a buscar.
     * @return un {@link Optional} con la caja registradora.
     */
    Optional<CashRegisterEntity> findByIdAndEnabledTrue(int id);

    /**
     * Encuentra todas las cajas registradoras no eliminadas.
     *
     * @return listado de cajas encontradas.
     */
    List<CashRegisterEntity> findAllByEnabledTrue();

    /**
     * Encuentra sólo el ID de todas las cajas registradoras no eliminadas.
     *
     * @return listado de IDs.
     */
    @Query("SELECT cr.id FROM CashRegister cr " +
            "WHERE cr.enabled = true")
    List<Integer> findAllIdsAndEnabledTrue();

    /**
     * Chequea si ya existe una caja registradora no eliminada según un ID.
     *
     * @param id el ID a chequear.
     * @return {@code true} si encuentra un registro.
     */
    boolean existsByIdAndEnabledTrue(int id);

    /**
     * Chequea si ya existe una caja registradora no eliminada según un numeral.
     *
     * @param numeral el numeral a chequear.
     * @return {@code true} si encuentra un registro.
     */
    boolean existsByNumeralAndEnabledTrue(int numeral);
}
