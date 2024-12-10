package com.servinetcomputers.api.domain.expense;

import com.servinetcomputers.api.domain.cashregister.abs.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.expense.abs.ExpenseMapper;
import com.servinetcomputers.api.domain.expense.abs.ExpenseRepository;
import com.servinetcomputers.api.domain.expense.abs.IExpenseService;
import com.servinetcomputers.api.domain.expense.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.dto.ExpenseResponse;
import com.servinetcomputers.api.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements IExpenseService {

    private final ExpenseRepository repository;
    private final ExpenseMapper mapper;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;

    @Override
    public ExpenseResponse create(ExpenseRequest request) {
        if (!cashRegisterDetailRepository.existsByIdAndEnabledTrue(request.cashRegisterDetailId())) {
            throw new NotFoundException("Jornada #" + request.cashRegisterDetailId() + " no encontrada");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }
    
}
