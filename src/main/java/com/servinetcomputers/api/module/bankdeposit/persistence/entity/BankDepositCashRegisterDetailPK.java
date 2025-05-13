package com.servinetcomputers.api.module.bankdeposit.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class BankDepositCashRegisterDetailPK implements Serializable {
    @Column(name = "bank_deposit_id")
    private Integer bankDepositId;

    @Column(name = "cash_register_detail_id")
    private Integer cashRegisterDetailId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankDepositCashRegisterDetailPK that = (BankDepositCashRegisterDetailPK) o;
        return Objects.equals(bankDepositId, that.bankDepositId) && Objects.equals(cashRegisterDetailId, that.cashRegisterDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankDepositId, cashRegisterDetailId);
    }
}
