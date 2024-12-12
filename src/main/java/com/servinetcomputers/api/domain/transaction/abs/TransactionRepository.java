package com.servinetcomputers.api.domain.transaction.abs;

import com.servinetcomputers.api.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByEnabledTrue();
}
