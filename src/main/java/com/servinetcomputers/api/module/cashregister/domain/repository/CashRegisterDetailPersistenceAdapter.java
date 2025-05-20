package com.servinetcomputers.api.module.cashregister.domain.repository;

import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.core.util.enums.CashRegisterStatus;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDetailDto;
import com.servinetcomputers.api.module.user.domain.dto.UserFullNameDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CashRegisterDetailPersistenceAdapter {
    void save(CreateCashRegisterDetailDto request);

    CashRegisterDetailDto save(CashRegisterDetailDto response);

    Integer getCurrentAmount();

    /**
     * Obtiene el último movimiento de caja registrado según el ID de la caja registradora.
     *
     * @param cashRegisterId el {@code ID} de la caja registradora.
     * @return el movimiento de caja encontrado.
     */
    CashRegisterDetailDto getLatestByCashRegisterId(Integer cashRegisterId);

    /**
     * Verify if a user already has a cash register detail that its status is not the specified.
     *
     * @param userId    the user id.
     * @param startDate the start date.
     * @param endDate   the end date.
     * @param status    the status.
     * @return true if already exists.
     */
    boolean existsByUserIdAndStatusNot(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterStatus status);

    List<CashRegisterDetailDto> getAllByUserIdBetween(int userId, LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetailDto> getAllByUserIdWhereStatusIsNotBetween(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterStatus status);

    List<CashRegisterDetailDto> getAllBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetailDto> getAllByStatusNotAndBefore(CashRegisterDetailStatus status, LocalDateTime createdDate);

    List<CashRegisterDetailDto> getAllByCashRegisterId(int cashRegisterId);

    List<CashRegisterDetailDto> getAllWhereUserIdIsNotAndStatusNotAndBetween(int userId, CashRegisterDetailStatus status, LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetailDto> getLatestWhereCashRegisterIdIsIn(List<Integer> cashRegisterIds);

    List<CashRegisterDetailDto> getLatestWhereCashRegisterIdIsNotIn(List<Integer> cashRegisterIds);

    Optional<CashRegisterDetailDto> get(int cashRegisterDetailId);

    Optional<UserFullNameDto> getUserFullNameById(int cashRegisterDetailId);
}
