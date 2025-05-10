package com.servinetcomputers.api.module.bankdeposit.persistence;

import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositCashRegisterDetail;
import com.servinetcomputers.api.module.bankdeposit.persistence.entity.BankDepositCashRegisterDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaBankDepositCashRegisterDetailRepository extends JpaRepository<BankDepositCashRegisterDetail, BankDepositCashRegisterDetailPK> {
    @Query("SELECT SUM(bdcrd.value) FROM BankDepositCashRegisterDetail bdcrd " +
            "WHERE bdcrd.cashRegisterDetail.id = :cashRegisterDetailId " +
            "AND bdcrd.enabled = true")
    Integer sumAllByCashRegisterDetailIdAndEnabledTrue(Integer cashRegisterDetailId);
}
