package com.servinetcomputers.api.module.expense.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.exception.RequiredTempCodeException;
import com.servinetcomputers.api.core.util.enums.ChangeLogAction;
import com.servinetcomputers.api.core.util.enums.ChangeLogType;
import com.servinetcomputers.api.module.changelog.application.usecase.CreateChangeLogUseCase;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;
import com.servinetcomputers.api.module.expense.application.usecase.UpdateExpenseUseCase;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.module.expense.domain.dto.UpdateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateExpenseService implements UpdateExpenseUseCase {
    private final ExpenseRepository repository;
    private final TempCodeRepository tempCodeRepository;
    private final CreateChangeLogUseCase createChangeLogUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public ExpenseResponse call(Integer expenseId, UpdateExpenseDto dto) {
        if (dto.tempCode() == null) {
            throw new RequiredTempCodeException();
        }

        final var lastTempCode = tempCodeRepository.getLast()
                .orElseThrow(RequiredTempCodeException::new);

        if (!lastTempCode.getCode().equals(dto.tempCode())) {
            throw new InvalidTempCodeException();
        }

        final var expense = repository.get(expenseId)
                .orElseThrow(() -> new NotFoundException("No se encontró el gasto: " + expenseId));

        final var previousData = expense.copy();

        expense.setDescription(dto.description() != null ? dto.description() : expense.getDescription());
        expense.setValue(dto.value() != null ? dto.value() : expense.getValue());
        expense.setDiscount(dto.discount() != null ? dto.discount() : expense.isDiscount());

        final var newData = repository.save(expense);
        
        createChangeLog(previousData, newData);
        lastTempCode.setUsedBy(newData.getCashRegisterDetail().getUser());
        tempCodeRepository.save(lastTempCode);

        return newData;
    }

    private void createChangeLog(ExpenseResponse previousData, ExpenseResponse newData) {
        final var dto = new CreateChangeLogDto(
                ChangeLogAction.UPDATE,
                ChangeLogType.EXPENSE,
                previousData.getCashRegisterDetailId(),
                previousData.getCashRegisterDetail().getStatus(),
                previousData,
                newData
        );

        createChangeLogUseCase.call(dto);
    }
}
