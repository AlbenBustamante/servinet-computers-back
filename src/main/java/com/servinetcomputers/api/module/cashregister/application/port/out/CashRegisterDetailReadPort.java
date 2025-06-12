package com.servinetcomputers.api.module.cashregister.application.port.out;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.cashregister.domain.CashRegisterDetail;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.user.infrastructure.in.rest.dto.UserFullNameDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de lectura y salida para los movimientos de caja registradora.
 */
public interface CashRegisterDetailReadPort {

    Integer getCurrentAmount();

    /**
     * Obtiene el último movimiento de caja registrado según el ID de la caja registradora.
     *
     * @param cashRegisterId {@code ID} de la caja registradora.
     * @return movimiento de caja encontrado.
     */
    CashRegisterDetail getLatestByCashRegisterId(Integer cashRegisterId);

    /**
     * Verifica si un usuario ya tiene un movimiento de caja registradora donde su estado no sea el especificado en un rango de fechas.
     *
     * @param userId    {@code ID} del usuario.
     * @param startDate Fecha inicial.
     * @param endDate   Fecha final.
     * @param status    Estado actual del movimiento.
     * @return {@code true} si encuentra un registro.
     */
    boolean existsByUserIdAndStatusNot(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterDetailStatus status);

    /**
     * Obtiene todos los movimientos de caja registradora según un ID de usuario y un rango de fechas.
     *
     * @param userId    {@code ID} del usuario.
     * @param startDate Fecha inicial.
     * @param endDate   Fecha final.
     * @return listado de movimientos.
     */
    List<CashRegisterDetail> getAllByUserIdBetween(int userId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Obtiene todos los movimientos de caja registradora según un ID de usuario, un rango de fechas y que su estado no sea el especificado.
     *
     * @param userId    {@code ID} del usuario.
     * @param startDate Fecha inicial.
     * @param endDate   Fecha final.
     * @param status    Estado actual del movimiento.
     * @return Listado de movimientos.
     */
    List<CashRegisterDetail> getAllByUserIdWhereStatusIsNotBetween(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterDetailStatus status);

    /**
     * Obtiene todos los movimientos de caja registradora en un rango de fechas.
     *
     * @param startDate Fecha inicial.
     * @param endDate   Fecha final.
     * @return Listado de movimientos.
     */
    List<CashRegisterDetail> getAllBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Obtiene todos los movimientos de caja registradora donde su estado no sea el especificado y con fecha de registro anterior al especificado.
     *
     * @param status      Estado actual del movimiento.
     * @param createdDate Fecha máxima.
     * @return Listado de movimientos.
     */
    List<CashRegisterDetail> getAllByStatusNotAndBefore(CashRegisterDetailStatus status, LocalDateTime createdDate);

    /**
     * Obtiene todos los movimientos de caja registradora según un ID de caja registradora.
     *
     * @param cashRegisterId {@code ID} de la caja registradora.
     * @return Listado de movimientos.
     */
    List<CashRegisterDetail> getAllByCashRegisterId(int cashRegisterId);

    /**
     * Obtiene todos los movimientos de caja registradora donde el ID de usuario no sea el especificado, el estado no sea el especificado y en un rango de fechas.
     *
     * @param userId    {@code ID} del usuario.
     * @param status    Estado actual del movimiento.
     * @param startDate Fecha inicial.
     * @param endDate   Fecha final.
     * @return Listado de movimientos.
     */
    List<CashRegisterDetail> getAllWhereUserIdIsNotAndStatusNotAndBetween(int userId, CashRegisterDetailStatus status, LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetailDto> getLatestWhereCashRegisterIdIsIn(List<Integer> cashRegisterIds);

    List<CashRegisterDetailDto> getLatestWhereCashRegisterIdIsNotIn(List<Integer> cashRegisterIds);

    /**
     * Obtiene un movimiento de caja registrado según un ID.
     *
     * @param cashRegisterDetailId {@code ID} del movimiento de caja registradora.
     * @return {@link CashRegisterDetail} encontrado.
     */
    CashRegisterDetail get(int cashRegisterDetailId);

    Optional<UserFullNameDto> getUserFullNameById(int cashRegisterDetailId);

    /**
     * Obtiene la última base de una caja registradora según un ID.
     *
     * @param cashRegisterId {@code ID} de la caja registradora.
     * @return base encontrada.
     */
    String getLastFinalBase(int cashRegisterId);
}
