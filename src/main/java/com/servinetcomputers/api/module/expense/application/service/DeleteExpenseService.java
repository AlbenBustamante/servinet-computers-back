package com.servinetcomputers.api.module.expense.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.exception.RequiredTempCodeException;
import com.servinetcomputers.api.module.expense.application.usecase.DeleteExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteExpenseService implements DeleteExpenseUseCase {
    private final ExpenseRepository expenseRepository;
    private final TempCodeRepository tempCodeRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public void call(Integer expenseId, Integer tempCode) {
        if (tempCode == null) {
            throw new RequiredTempCodeException();
        }

        final var lastTempCode = tempCodeRepository.getLast();

        if (lastTempCode.isEmpty() || !lastTempCode.get().getCode().equals(tempCode)) {
            throw new InvalidTempCodeException();
        }

        final var expense = expenseRepository.get(expenseId)
                .orElseThrow(() -> new NotFoundException("No se encontró el gasto: " + expenseId));

        expense.setEnabled(false);
        expenseRepository.save(expense);

        lastTempCode.get().setUsedBy(expense.getCashRegisterDetail().getUser());
        tempCodeRepository.save(lastTempCode.get());
    }
}
