package com.servinetcomputers.api.module.bankdeposit.persistence.adapter;

import com.servinetcomputers.api.module.bankdeposit.domain.adapter.BankDepositCashRegisterDetailPersistenceAdapter;
import com.servinetcomputers.api.module.bankdeposit.persistence.JpaBankDepositCashRegisterDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BankDepositCashRegisterDetailPersistenceAdapterImpl implements BankDepositCashRegisterDetailPersistenceAdapter {
    private final JpaBankDepositCashRegisterDetailRepository jpaBankDepositCashRegisterDetailRepository;

    @Override
    public Integer sumValuesByCashRegisterDetailId(Integer cashRegisterDetailId) {
        final var values = jpaBankDepositCashRegisterDetailRepository.sumAllByCashRegisterDetailIdAndEnabledTrue(cashRegisterDetailId);
        return values != null ? values : 0;
    }
}
