package com.servinetcomputers.api.module.bankdeposit.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateBankDepositUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.repository.BankDepositRepository;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter1;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CreateBankDepositService implements CreateBankDepositUseCase {
    private static final String EXPENSE_PREFIX = "BANCO";
    private final BankDepositRepository repository;
    private final CashRegisterDetailPersistenceAdapter1 cashRegisterDetailPersistenceAdapter;
    private final ExpenseRepository expenseRepository;

    @Override
    public BankDepositDto call(CreateBankDepositDto dto) {
        final var cashRegisterDetail = cashRegisterDetailPersistenceAdapter.get(dto.getCashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada: " + dto.getCashRegisterDetailId()));

        dto.setCashRegisterDetailDto(cashRegisterDetail);

        if (dto.getExpenseNote() != null && dto.getExpenseValue() != null) {
            final var expenseNote = String.format("%s: %s", EXPENSE_PREFIX, dto.getExpenseNote());
            final var createExpenseDto = new CreateExpenseDto(dto.getCashRegisterDetailId(), expenseNote, dto.getExpenseValue(), false, true);
            createExpenseDto.setCashRegisterDetail(cashRegisterDetail);

            final var newExpense = expenseRepository.save(createExpenseDto);
            dto.setExpenseDto(newExpense);
        }

        dto.setStatus(BankDepositStatus.OPEN);

        final var newBankDeposit = repository.save(dto);
        newBankDeposit.setTotalCollected(0);

        return newBankDeposit;
    }
}
