package com.servinetcomputers.api.module.expense.persistence.repository;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.page.PaginationMapper;
import com.servinetcomputers.api.module.expense.domain.dto.CreateExpenseDto;
import com.servinetcomputers.api.module.expense.domain.dto.ExpenseDto;
import com.servinetcomputers.api.module.expense.domain.repository.ExpenseRepository;
import com.servinetcomputers.api.module.expense.persistence.JpaExpenseRepository;
import com.servinetcomputers.api.module.expense.persistence.mapper.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private final JpaExpenseRepository repository;
    private final ExpenseMapper mapper;
    private final PaginationMapper paginationMapper;

    @Override
    public ExpenseDto save(CreateExpenseDto request) {
        final var entity = mapper.toEntity(request);
        final var newExpense = repository.save(entity);

        return mapper.toDto(newExpense);
    }

    @Override
    public ExpenseDto save(ExpenseDto response) {
        final var entity = mapper.toEntity(response);
        final var newExpense = repository.save(entity);

        return mapper.toDto(newExpense);
    }

    @Override
    public Optional<ExpenseDto> get(int expenseId) {
        final var expense = repository.findByIdAndEnabledTrue(expenseId);
        return expense.map(mapper::toDto);
    }

    @Override
    public Optional<ExpenseDto> getDeleted(int expenseId) {
        final var expense = repository.findById(expenseId);
        return expense.map(mapper::toDto);
    }

    @Override
    public PageResponse<ExpenseDto> getAllByCashRegisterDetailId(int cashRegisterDetailId, Pageable pageable) {
        final var page = repository.findAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId, pageable);
        final var expenses = mapper.toDto(page.getContent());

        return new PageResponse<>(paginationMapper.toPagination(page), expenses);
    }

    @Override
    public List<ExpenseDto> getAllByCashRegisterDetailId(int cashRegisterDetailId) {
        final var expenses = repository.findAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId);
        return mapper.toDto(expenses);
    }

    @Override
    public List<ExpenseDto> getAllByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount) {
        final var expenses = repository.findAllByCashRegisterDetailIdAndDiscountAndEnabledTrue(cashRegisterDetailId, discount);
        return mapper.toDto(expenses);
    }

    @Override
    public List<ExpenseDto> getAllByDiscountAndCodeCodeBetween(boolean discount, String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var expenses = repository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetweenAndDiscount(code, startDate, endDate, discount);
        return mapper.toDto(expenses);
    }

    @Override
    public Integer sumValuesByCashRegisterDetailIdAndDiscount(int cashRegisterDetailId, boolean discount) {
        final var expenses = repository.sumAllByCashRegisterDetailIdAndDiscount(cashRegisterDetailId, discount);
        return expenses != null ? expenses : 0;
    }

    @Override
    public int sumValuesBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var values = repository.sumAllValuesByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return values != null ? values : 0;
    }
}
