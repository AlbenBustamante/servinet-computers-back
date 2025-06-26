package com.servinetcomputers.api.module.expense.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.expense.application.usecase.CreateExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateExpenseService implements CreateExpenseUseCase {
    private final ExpenseRepository repository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public PageResponse<ExpenseDto> call(CreateExpenseDto request, Pageable pageable) {
        final var cashRegisterDetail = cashRegisterDetailRepository.get(request.getCashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("Jornada #" + request.getCashRegisterDetailId() + " no encontrada"));

        request.setCashRegisterDetail(cashRegisterDetail);
        repository.save(request);

        return repository.getAllByCashRegisterDetailId(cashRegisterDetail.getId(), pageable);
    }
}
