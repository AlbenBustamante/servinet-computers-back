package com.servinetcomputers.api.module.platform.application.service.transfer;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.DeletePlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.GetPlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeletePlatformTransferService implements DeletePlatformTransferUseCase {
    private final PlatformTransferRepository repository;
    private final GetPlatformTransferUseCase getPlatformTransferUseCase;

    /**
     * Disable an existing and available transfer.
     *
     * @param transferId the ID to be searched.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public void call(Integer transferId) {
        final var transfer = getPlatformTransferUseCase.call(transferId);
        transfer.setEnabled(false);

        repository.save(transfer);
    }
}
