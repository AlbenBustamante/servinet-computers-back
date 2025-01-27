package com.servinetcomputers.api.domain.cashregister.domain.repository;

import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailReportsDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CashRegisterDetailRepository {
    void save(CashRegisterDetailRequest request);

    CashRegisterDetailResponse save(CashRegisterDetailResponse response);

    boolean existsById(int id);

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

    List<CashRegisterDetailResponse> getAllByUserIdBetween(int userId, LocalDateTime startDate, LocalDateTime endDate);

    List<CashRegisterDetailResponse> getAllByUserIdWhereStatusIsNotBetween(int userId, LocalDateTime startDate, LocalDateTime endDate, CashRegisterStatus status);

    List<CashRegisterDetailResponse> getAllBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<CashRegisterDetailResponse> get(int cashRegisterDetailId);

    CashRegisterDetailReportsDto getCashRegisterDetailReports(int cashRegisterDetailId);
}
