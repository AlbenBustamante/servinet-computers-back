package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterDetailResponse;

public interface ICashRegisterDetailService {

    CashRegisterDetailResponse create(CashRegisterDetailRequest request);

    boolean isAlreadyCreated();

    CashRegisterDetailResponse get();

    CashRegisterDetailResponse updateHours(CashRegisterDetailRequest req);

    boolean delete(int id);

}
