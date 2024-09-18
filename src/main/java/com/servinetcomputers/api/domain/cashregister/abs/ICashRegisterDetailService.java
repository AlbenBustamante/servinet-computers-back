package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.AlreadyExistsCashRegisterDetailDto;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailResponse;
import com.servinetcomputers.api.domain.cashregister.dto.MyCashRegistersReports;

public interface ICashRegisterDetailService {

    MyCashRegistersReports create(CashRegisterDetailRequest request);

    AlreadyExistsCashRegisterDetailDto alreadyExists();

    CashRegisterDetailResponse getById(int cashRegisterDetailId);

    MyCashRegistersReports getReportsByUserId(int userId);

    CashRegisterDetailResponse updateHours(int cashRegisterDetailId, CashRegisterDetailRequest req);

    boolean delete(int id);

}
