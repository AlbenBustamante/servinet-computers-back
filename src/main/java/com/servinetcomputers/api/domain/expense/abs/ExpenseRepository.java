package com.servinetcomputers.api.domain.expense.abs;

import com.servinetcomputers.api.domain.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
