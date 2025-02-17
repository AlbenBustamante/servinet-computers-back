package com.servinetcomputers.api.module.transaction.persistence.repository;

import com.servinetcomputers.api.core.util.enums.TransactionDetailType;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailRequest;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.module.transaction.persistence.JpaTransactionDetailRepository;
import com.servinetcomputers.api.module.transaction.persistence.mapper.TransactionDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionDetailRepositoryImpl implements TransactionDetailRepository {
    private final JpaTransactionDetailRepository repository;
    private final TransactionDetailMapper mapper;

    @Override
    public TransactionDetailResponse save(TransactionDetailRequest request) {
        final var entity = mapper.toEntity(request);
        final var newDetail = repository.save(entity);

        return mapper.toResponse(newDetail);
    }

    @Override
    public List<TransactionDetailResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId) {
        final var details = repository.findAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId);
        return mapper.toResponses(details);
    }

    @Override
    public List<TransactionDetailResponse> getAllByCodeBetween(String code, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByCreatedByAndEnabledTrueAndCreatedDateBetween(code, startDate, endDate);
        return mapper.toResponses(details);
    }

    @Override
    public Integer sumValuesByCashRegisterDetailIdAndType(int cashRegisterDetailId, TransactionDetailType type) {
        final var deposits = repository.sumAllByCashRegisterDetailIdAndEnabledTrueAndType(cashRegisterDetailId, type);
        return deposits != null ? deposits : 0;
    }

    @Override
    public int countByCashRegisterDetailId(int cashRegisterDetailId) {
        final var transactions = repository.countByIdAndEnabledTrue(cashRegisterDetailId);
        return transactions == null ? 0 : transactions;
    }

    @Override
    public int sumCommissionBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var commission = repository.sumAllCommissionByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return commission != null ? commission : 0;
    }

    @Override
    public int countBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var transactions = repository.countByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return transactions != null ? transactions : 0;
    }
}
