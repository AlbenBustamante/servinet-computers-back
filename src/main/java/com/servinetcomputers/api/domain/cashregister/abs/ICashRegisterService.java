package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;

import java.util.List;

public interface ICashRegisterService {

    CashRegisterResponse create(CashRegisterRequest request);

    List<CashRegisterResponse> getAll(boolean enabled);

    CashRegisterResponse update(int id, CashRegisterRequest request);

    CashRegisterResponse updateStatus(CashRegisterRequest request);

    boolean delete(int id);

}
