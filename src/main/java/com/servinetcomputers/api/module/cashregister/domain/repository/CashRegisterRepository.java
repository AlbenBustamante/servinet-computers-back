package com.servinetcomputers.api.module.cashregister.domain.repository;

import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDto;
import com.servinetcomputers.api.module.cashregister.domain.dto.CreateCashRegisterDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CashRegisterRepository {
    CashRegisterDto save(CreateCashRegisterDto request);

    CashRegisterDto save(CashRegisterDto response);

    Optional<CashRegisterDto> get(int id);

    List<CashRegisterDto> getAll();

    List<Integer> getAllIds();

    boolean existsById(int id);

    boolean existsByNumeral(int numeral);

    Page<String> getLastFinalBase(int cashRegisterId);
}
