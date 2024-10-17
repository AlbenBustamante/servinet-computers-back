package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.dto.*;

public interface ICashRegisterDetailService {

    MyCashRegistersReports create(CashRegisterDetailRequest request);

    AlreadyExistsCashRegisterDetailDto alreadyExists();

    CashRegisterDetailResponse getById(int cashRegisterDetailId);

    MyCashRegistersReports getReportsByUserId(int userId);

    CashRegisterDetailReportsDto getCashRegisterDetailReports(int cashRegisterDetailId);

    CashRegisterDetailResponse startBrake(int cashRegisterDetailId);

    CashRegisterDetailResponse endBrake(int cashRegisterDetailId);

    CashRegisterDetailReportsDto close(int cashRegisterDetailId, BaseDto finalBase);

    boolean delete(int id);

}
