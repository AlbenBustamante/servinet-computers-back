package com.servinetcomputers.api.domain.transaction.persistence;

import com.servinetcomputers.api.domain.transaction.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaTransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByEnabledTrueOrderByUsesAsc();

    Optional<Transaction> findByDescriptionAndEnabledTrue(String description);
}
