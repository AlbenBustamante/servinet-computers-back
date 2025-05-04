package com.servinetcomputers.api.module.transaction.persistence.repository;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.page.PaginationMapper;
import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.module.transaction.persistence.JpaTransactionDetailRepository;
import com.servinetcomputers.api.module.transaction.persistence.mapper.TransactionDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TransactionDetailRepositoryImpl implements TransactionDetailRepository {
    private final JpaTransactionDetailRepository repository;
    private final TransactionDetailMapper mapper;
    private final PaginationMapper paginationMapper;

    @Override
    public TransactionDetailDto save(CreateTransactionDetailDto request) {
        final var entity = mapper.toEntity(request);
        final var newDetail = repository.save(entity);

        return mapper.toDto(newDetail);
    }

    @Override
    public TransactionDetailDto save(TransactionDetailDto response) {
        final var entity = mapper.toEntity(response);
        final var newDetail = repository.save(entity);

        return mapper.toDto(newDetail);
    }

    @Override
    public Optional<TransactionDetailDto> get(int transactionDetailId) {
        final var detail = repository.findByIdAndEnabledTrue(transactionDetailId);
        return detail.map(mapper::toDto);
    }

    @Override
    public List<TransactionDetailDto> getAllByCashRegisterDetailId(int cashRegisterDetailId) {
        final var details = repository.findAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId);
        return mapper.toDto(details);
    }

    @Override
    public PageResponse<TransactionDetailDto> getAllByCashRegisterDetailId(int cashRegisterDetailId, Pageable pageable) {
        final var page = repository.findAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId, pageable);
        final var details = mapper.toDto(page.getContent());

        return new PageResponse<>(paginationMapper.toPagination(page), details);
    }

    @Override
    public List<TransactionDetailDto> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate);
        return mapper.toDto(details);
    }

    @Override
    public Integer sumValuesByCashRegisterDetailIdAndType(int cashRegisterDetailId, TransactionDetailType type) {
        final var deposits = repository.sumAllByCashRegisterDetailIdAndEnabledTrueAndType(cashRegisterDetailId, type);
        return deposits != null ? deposits : 0;
    }

    @Override
    public int countByCashRegisterDetailId(int cashRegisterDetailId) {
        final var transactions = repository.countByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId);
        return transactions == null ? 0 : transactions;
    }

    @Override
    public int sumCommissionBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var commission = repository.sumAllCommissionByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return commission != null ? commission : 0;
    }

    @Override
    public int sumCommissionByCashRegisterDetailId(int cashRegisterDetailId) {
        final var commission = repository.sumAllCommissionByEnabledTrueAndCashRegisterDetailId(cashRegisterDetailId);
        return commission != null ? commission : 0;
    }

    @Override
    public int countBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var transactions = repository.countByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return transactions != null ? transactions : 0;
    }
}
