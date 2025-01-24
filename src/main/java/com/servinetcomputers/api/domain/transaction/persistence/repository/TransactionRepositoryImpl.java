package com.servinetcomputers.api.domain.transaction.persistence.repository;

import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionRequest;
import com.servinetcomputers.api.domain.transaction.domain.dto.TransactionResponse;
import com.servinetcomputers.api.domain.transaction.domain.repository.TransactionRepository;
import com.servinetcomputers.api.domain.transaction.persistence.JpaTransactionRepository;
import com.servinetcomputers.api.domain.transaction.persistence.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final JpaTransactionRepository repository;
    private final TransactionMapper mapper;

    @Override
    public TransactionResponse save(TransactionRequest request) {
        final var entity = mapper.toEntity(request);
        final var newTransaction = repository.save(entity);

        return mapper.toResponse(newTransaction);
    }

    @Override
    public TransactionResponse save(TransactionResponse response) {
        final var entity = mapper.toEntity(response);
        final var newTransaction = repository.save(entity);
        
        return mapper.toResponse(newTransaction);
    }

    @Override
    public List<TransactionResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrueOrderByUsesAsc());
    }

    @Override
    public Optional<TransactionResponse> getByDescription(String description) {
        final var transaction = repository.findByDescriptionAndEnabledTrue(description);
        return transaction.map(mapper::toResponse);
    }
}
