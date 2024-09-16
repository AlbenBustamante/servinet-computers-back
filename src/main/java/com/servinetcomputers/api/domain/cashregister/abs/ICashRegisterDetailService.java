package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.*;

public interface ICashRegisterDetailService {

    CashRegisterDetailReportsDto create(CashRegisterDetailRequest request);

    AlreadyExistsCashRegisterDetailDto alreadyExists();

    CashRegisterDetailResponse getById(int cashRegisterDetailId);

    MyCashRegistersReports getReportsByUserId(int userId);

    CashRegisterDetailResponse updateHours(int cashRegisterDetailId, CashRegisterDetailRequest req);

    boolean delete(int id);

}
