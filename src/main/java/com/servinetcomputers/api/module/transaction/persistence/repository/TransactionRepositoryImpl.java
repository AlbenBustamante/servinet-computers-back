package com.servinetcomputers.api.module.transaction.persistence.repository;

import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDto;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionRepository;
import com.servinetcomputers.api.module.transaction.persistence.JpaTransactionRepository;
import com.servinetcomputers.api.module.transaction.persistence.mapper.TransactionMapper;
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
    public TransactionDto save(CreateTransactionDto request) {
        final var entity = mapper.toEntity(request);
        final var newTransaction = repository.save(entity);

        return mapper.toDto(newTransaction);
    }

    @Override
    public TransactionDto save(TransactionDto response) {
        final var entity = mapper.toEntity(response);
        final var newTransaction = repository.save(entity);

        return mapper.toDto(newTransaction);
    }

    @Override
    public List<TransactionDto> getAllOrderByUsesDesc() {
        return mapper.toDto(repository.findAllByEnabledTrueOrderByUsesDesc());
    }

    @Override
    public Optional<TransactionDto> getByDescription(String description) {
        final var transaction = repository.findByDescriptionAndEnabledTrue(description);
        return transaction.map(mapper::toDto);
    }
}
