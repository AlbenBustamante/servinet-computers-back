package com.servinetcomputers.api.domain.cashregister.domain.repository;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.*;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface CashRegisterDetailRepository {
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

    List<CashRegisterDetailResponse> getAllByUserId(int userId, LocalDateTime startDate, LocalDateTime endDate);

    void save(CashRegisterDetailRequest request);

    List<CashRegisterDetailResponse> getAllOfToday();

    AlreadyExistsCashRegisterDetailDto alreadyExists();

    CashRegisterDetailResponse getById(int cashRegisterDetailId);

    MyCashRegistersReports getReportsByUserId(int userId);

    CashRegisterDetailReportsDto getCashRegisterDetailReports(int cashRegisterDetailId);

    CashRegisterDetailResponse startBreak(int cashRegisterDetailId);

    CashRegisterDetailResponse endBreak(int cashRegisterDetailId);

    CashRegisterDetailReportsDto close(int cashRegisterDetailId, BaseDto finalBase);

    boolean delete(int id);
}
