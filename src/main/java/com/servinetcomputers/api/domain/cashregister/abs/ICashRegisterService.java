package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.cashregister.model.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.model.dto.CashRegisterResponse;

import java.util.List;

public interface ICashRegisterService {

    CashRegisterResponse create(CashRegisterRequest request);

    List<CashRegisterResponse> getAll();

    CashRegisterResponse updateStatus(CashRegisterRequest request);

    boolean delete(int id);

}
