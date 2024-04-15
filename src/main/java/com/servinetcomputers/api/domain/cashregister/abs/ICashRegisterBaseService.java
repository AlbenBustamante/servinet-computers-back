package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterBaseResponse;

import java.util.List;

public interface ICashRegisterBaseService {

    CashRegisterBaseResponse create(CashRegisterBaseRequest request);

    List<CashRegisterBaseResponse> getByCashRegisterDetailId(int cashRegisterDetailId);

}
