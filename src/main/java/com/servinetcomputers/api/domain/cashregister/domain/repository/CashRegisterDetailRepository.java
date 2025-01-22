package com.servinetcomputers.api.domain.cashregister.domain.repository;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.*;

import java.util.List;

public interface CashRegisterDetailRepository {
    boolean existsById(int id);

    MyCashRegistersReports create(CashRegisterDetailRequest request);

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
