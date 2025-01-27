package com.servinetcomputers.api.domain.cashregister.domain.repository;

import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CashRegisterRepository {
    CashRegisterResponse save(CashRegisterRequest request);

    CashRegisterResponse save(CashRegisterResponse response);

    Optional<CashRegisterResponse> get(int id);

    List<CashRegisterResponse> getAll();

    boolean existsByNumeral(int numeral);

    Page<String> getLastFinalBaseFromCashRegisterId(int cashRegisterId);
}
