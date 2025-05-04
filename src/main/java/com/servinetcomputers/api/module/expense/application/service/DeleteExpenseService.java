package com.servinetcomputers.api.module.expense.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.exception.RequiredTempCodeException;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.changelog.application.usecase.CreateChangeLogUseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.expense.application.usecase.DeleteExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
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
    private final CreateChangeLogUseCase createChangeLogUseCase;

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

        final var previousData = expense.copy();

        expense.setEnabled(false);
        expenseRepository.save(expense);

        createChangeLog(previousData);

        lastTempCode.get().setUsedBy(expense.getCashRegisterDetail().getUser());
        tempCodeRepository.save(lastTempCode.get());
    }

    private void createChangeLog(ExpenseDto previousData) {
        final var dto = new CreateChangeLogDto(
                ChangeLogAction.DELETE,
                ChangeLogType.EXPENSE,
                previousData.getCashRegisterDetailId(),
                previousData.getCashRegisterDetail().getStatus(),
                previousData,
                null
        );

        createChangeLogUseCase.call(dto);
    }
}
