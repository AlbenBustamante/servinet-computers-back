package com.servinetcomputers.api.module.transaction.application.service;

import com.servinetcomputers.api.module.transaction.application.usecase.UpdateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailResponse;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateTransactionDetailService implements UpdateTransactionDetailUseCase {
    @Override
    public TransactionDetailResponse call(Integer transactionDetailId, UpdateTransactionDetailDto dto) {
        return null;
    }
}
