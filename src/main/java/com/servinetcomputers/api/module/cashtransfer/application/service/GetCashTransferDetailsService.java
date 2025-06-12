package com.servinetcomputers.api.module.cashtransfer.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailPersistenceAdapter1;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetCashTransferDetailsUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class GetCashTransferDetailsService implements GetCashTransferDetailsUseCase {
    private final CashRegisterDetailPersistenceAdapter1 cashRegisterDetailPersistenceAdapter;
    private final SafeDetailRepository safeDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public CashTransferDto call(CashTransferDto dto, Integer cashRegisterDetailId) {
        final var received = dto.getReceiverType() == CashBoxType.CASH_REGISTER && dto.getReceiverId().equals(cashRegisterDetailId);
        final var sender = getNames(dto.getSenderType(), dto.getSenderId());
        final var receiver = getNames(dto.getReceiverType(), dto.getReceiverId());

        return dto.copyWithDetails(received, receiver, sender);
    }

    private String getNames(CashBoxType type, int id) {
        if (type == CashBoxType.CASH_REGISTER) {
            return cashRegisterDetailPersistenceAdapter.getUserFullNameById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró el usuario suministrado: #" + id))
                    .fullName();
        }

        if (type == CashBoxType.SAFE) {
            final var numeral = safeDetailRepository.getNumeralById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró la caja suministrada: #" + id));
            return "Caja Fuerte N°".concat(String.valueOf(numeral));
        }

        return "-";
    }
}
