package com.servinetcomputers.api.domain.expense.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.expense.application.usecase.CreateExpenseUseCase;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.domain.expense.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateExpenseService implements CreateExpenseUseCase {
    private final ExpenseRepository repository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public ExpenseResponse call(ExpenseRequest param) {
        if (!cashRegisterDetailRepository.existsById(param.cashRegisterDetailId())) {
            throw new NotFoundException("Jornada #" + param.cashRegisterDetailId() + " no encontrada");
        }
        
        return repository.save(param);
    }
}
