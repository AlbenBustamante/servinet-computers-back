package com.servinetcomputers.api.domain.cashregister.abs;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.dto.CashRegisterResponse;

import java.util.List;

public interface ICashRegisterService {

    CashRegisterResponse create(CashRegisterRequest request);

    List<CashRegisterResponse> getAll();

    BaseDto getLastFinalBaseFromCashRegisterId(int cashRegisterId);

    CashRegisterResponse update(int id, CashRegisterRequest request);

    boolean delete(int id);

}
