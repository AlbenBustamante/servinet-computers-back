package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.InvalidTempCodeException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.tempcode.domain.repository.TempCodeRepository;
import com.servinetcomputers.api.module.transaction.application.usecase.DeleteTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.repository.TransactionDetailRepository;
import com.servinetcomputers.api.module.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteTransactionDetailService implements DeleteTransactionDetailUseCase {
    private final TransactionDetailRepository transactionDetailRepository;
    private final TempCodeRepository tempCodeRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public void call(Integer transactionDetailId, Integer tempCode) {
        if (tempCode == null) {
            throw new BadRequestException("El código es requerido");
        }

        final var lastTempCode = tempCodeRepository.getLast();

        if (lastTempCode.isEmpty() || !lastTempCode.get().getCode().equals(tempCode)) {
            throw new InvalidTempCodeException();
        }

        final var transactionDetail = transactionDetailRepository.get(transactionDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontró la jornada #" + transactionDetailId));

        transactionDetail.setEnabled(false);
        transactionDetailRepository.save(transactionDetail);

        final var userId = transactionDetail.getCashRegisterDetail().getUserId();
        final var user = userRepository.get(userId)
                .orElseThrow(() -> new NotFoundException("No se encontró al usuario: #" + userId));

        lastTempCode.get().setUsedBy(user);
        tempCodeRepository.save(lastTempCode.get());
    }
}
