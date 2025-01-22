package com.servinetcomputers.api.domain.expense.persistence.repository;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.cashregister.persistence.JpaCashRegisterDetailRepository;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseRequest;
import com.servinetcomputers.api.domain.expense.domain.dto.ExpenseResponse;
import com.servinetcomputers.api.domain.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.domain.expense.persistence.JpaExpenseRepository;
import com.servinetcomputers.api.domain.expense.persistence.mapper.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final JpaExpenseRepository repository;
    private final JpaCashRegisterDetailRepository jpaCashRegisterDetailRepository;
    private final ExpenseMapper mapper;

    @Override
    public ExpenseResponse create(ExpenseRequest request) {
        if (!jpaCashRegisterDetailRepository.existsByIdAndEnabledTrue(request.cashRegisterDetailId())) {
            throw new NotFoundException("Jornada #" + request.cashRegisterDetailId() + " no encontrada");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public List<ExpenseResponse> getByCashRegisterDetailId(int cashRegisterDetailId) {
        final var today = LocalDate.now();
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now());

        final var expenses = repository.findAllByCashRegisterDetailIdAndEnabledTrueAndCreatedDateBetween(cashRegisterDetailId, startDate, endDate);

        return mapper.toResponses(expenses);
    }
}
