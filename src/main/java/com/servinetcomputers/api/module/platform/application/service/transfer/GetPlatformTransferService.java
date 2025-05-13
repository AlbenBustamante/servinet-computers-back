package com.servinetcomputers.api.module.platform.application.service.transfer;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.GetPlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetPlatformTransferService implements GetPlatformTransferUseCase {
    private final PlatformTransferRepository repository;

    /**
     * Search an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     * @return the transfer found.
     */
    @Transactional(readOnly = true)
    @Override
    public PlatformTransferDto call(Integer transferId) {
        return repository.get(transferId)
                .orElseThrow(() -> new NotFoundException("Transferencia no encontrada: #" + transferId));
    }
}
