package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseResponse;

public interface ICashRegisterBaseService {

    CashRegisterBaseResponse create(CashRegisterBaseRequest request);

    CashRegisterBaseResponse getByCashRegisterDetailId(int cashRegisterDetailId);

    CashRegisterBaseResponse getLastBaseFromCashRegisterId(int cashRegisterId);

}
