package com.servinetcomputers.api.module.cashregister.domain.repository;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterRequest;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CashRegisterRepository {
    CashRegisterResponse save(CashRegisterRequest request);

    CashRegisterResponse save(CashRegisterResponse response);

    Optional<CashRegisterResponse> get(int id);

    List<CashRegisterResponse> getAll();

    List<Integer> getAllIds();

    boolean existsById(int id);

    boolean existsByNumeral(int numeral);

    Page<String> getLastFinalBase(int cashRegisterId);
}
