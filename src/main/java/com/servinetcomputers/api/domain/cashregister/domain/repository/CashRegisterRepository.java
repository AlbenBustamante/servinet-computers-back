package com.servinetcomputers.api.domain.cashregister.domain.repository;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.domain.dto.UpdateCashRegisterDto;

import java.util.List;

public interface CashRegisterRepository {
    CashRegisterResponse create(CashRegisterRequest request);

    List<CashRegisterResponse> getAll();

    BaseDto getLastFinalBaseFromCashRegisterId(int cashRegisterId);

    CashRegisterResponse update(UpdateCashRegisterDto updateCashRegisterDto);

    boolean delete(int id);
}
