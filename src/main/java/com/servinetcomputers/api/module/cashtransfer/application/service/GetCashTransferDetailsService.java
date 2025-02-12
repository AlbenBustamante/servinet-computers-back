package com.servinetcomputers.api.module.cashtransfer.application.service;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.security.service.UserLoggedService;
import com.servinetcomputers.api.core.util.enums.CashBoxType;
import com.servinetcomputers.api.module.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetCashTransferDetailsUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class GetCashTransferDetailsService implements GetCashTransferDetailsUseCase {
    private final UserLoggedService userLoggedService;
    private final CashRegisterDetailRepository cashRegisterDetailRepository;
    private final SafeDetailRepository safeDetailRepository;

    @Transactional(readOnly = true)
    @Override
    public CashTransferDto call(CashTransferDto dto) {
        final var userId = userLoggedService.id();
        final var received = dto.receiverType() == CashBoxType.CASH_REGISTER && dto.receiverId() == userId;
        final var sender = getNames(dto.senderType(), dto.senderId());
        final var receiver = getNames(dto.receiverType(), dto.receiverId());

        return dto.copyWithDetails(received, receiver, sender);
    }

    private String getNames(CashBoxType type, int id) {
        if (type == CashBoxType.CASH_REGISTER) {
            return cashRegisterDetailRepository.getUserFullNameById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró el usuario suministrado"))
                    .fullName();
        }

        if (type == CashBoxType.SAFE) {
            final var numeral = safeDetailRepository.getNumeralById(id)
                    .orElseThrow(() -> new NotFoundException("No se encontró a caja suministrada"));
            return "Caja N°".concat(String.valueOf(numeral));
        }

        return "-";
    }
}
