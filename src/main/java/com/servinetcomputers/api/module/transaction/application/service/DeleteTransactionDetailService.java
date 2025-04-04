package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import com.servinetcomputers.api.module.transaction.application.usecase.DeleteTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteTransactionDetailService implements DeleteTransactionDetailUseCase {
    private final TransactionDetailRepository transactionDetailRepository;
    private final TempCodeRepository tempCodeRepository;

    @Override
    public Boolean call(Integer transactionDetailId, Integer tempCode) {
        if (tempCode == null) {
            throw new BadRequestException("El código es requerido");
        }

        final var lastTempCode = tempCodeRepository.getLast();

        if (lastTempCode.isEmpty() || !lastTempCode.get().getCode().equals(tempCode)) {
            return false;
        }

        final var transactionDetail = transactionDetailRepository.get(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada #" + transactionDetailId));

        transactionDetail.setEnabled(false);
        transactionDetailRepository.save(transactionDetail);

        return true;
    }
}
