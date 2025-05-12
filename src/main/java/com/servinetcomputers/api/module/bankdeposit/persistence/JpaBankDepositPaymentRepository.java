package com.servinetcomputers.api.module.bankdeposit.persistence;

import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBankDepositPaymentRepository extends JpaRepository<BankDepositPayment, Integer> {
}
