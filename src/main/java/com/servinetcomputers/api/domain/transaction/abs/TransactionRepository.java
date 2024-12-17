package com.servinetcomputers.api.domain.transaction.abs;

import com.servinetcomputers.api.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t.description FROM Transaction t WHERE t.enabled = true")
    List<String> findAllDescriptions();

    Optional<Transaction> findByIdAndEnabledTrue(int cashRegisterDetailId);
}
