package com.servinetcomputers.api.module.bankdeposit.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.BankDepositStatus;
import com.servinetcomputers.api.module.bankdeposit.application.usecase.CreateBankDepositUseCase;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.BankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.dto.CreateBankDepositDto;
import com.servinetcomputers.api.module.bankdeposit.domain.repository.BankDepositRepository;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateBankDepositService implements CreateBankDepositUseCase {
    private final BankDepositRepository repository;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final ExpenseRepository expenseRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BankDepositDto call(CreateBankDepositDto dto) {
        final var cashRegisterDetail = cashRegisterDetailRepository.get(dto.getCashRegisterDetailId())
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada: " + dto.getCashRegisterDetailId()));

        dto.setCashRegisterDetailDto(cashRegisterDetail);

        if (dto.getExpenseNote() != null && dto.getExpenseValue() != null) {
            final var createExpenseDto = new CreateExpenseDto(dto.getCashRegisterDetailId(), dto.getExpenseNote(), dto.getExpenseValue(), false, true);
            final var newExpense = expenseRepository.save(createExpenseDto);

            dto.setExpenseDto(newExpense);
        }

        dto.setStatus(BankDepositStatus.OPEN);

        return repository.save(dto);
    }
}
