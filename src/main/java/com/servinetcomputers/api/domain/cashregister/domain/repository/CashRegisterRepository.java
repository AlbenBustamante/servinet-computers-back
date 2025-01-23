package com.servinetcomputers.api.domain.cashregister.domain.repository;

import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterResponse;
import com.servinetcomputers.api.domain.cashregister.domain.dto.UpdateCashRegisterDto;

import java.util.List;
import java.util.Optional;

public interface CashRegisterRepository {
    Optional<CashRegisterResponse> get(int id);

    CashRegisterResponse save(CashRegisterResponse response);

    CashRegisterResponse create(CashRegisterRequest request);

    List<CashRegisterResponse> getAll();

    BaseDto getLastFinalBaseFromCashRegisterId(int cashRegisterId);

    CashRegisterResponse update(UpdateCashRegisterDto updateCashRegisterDto);

    boolean delete(int id);
}
