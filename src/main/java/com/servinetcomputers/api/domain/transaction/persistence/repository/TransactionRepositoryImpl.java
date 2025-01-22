package com.servinetcomputers.api.domain.transaction.persistence.repository;

import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionResponse;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionRepository;
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionRepository;
import com.servinetcomputers.api.domain.transaction.persistence.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final JpaTransactionRepository repository;
    private final TransactionMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrueOrderByUsesAsc());
    }
}
