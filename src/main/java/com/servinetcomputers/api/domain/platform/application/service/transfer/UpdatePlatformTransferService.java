package com.servinetcomputers.api.domain.platform.application.service.transfer;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.platform.application.usecase.transfer.GetPlatformTransferUseCase;
import com.servinetcomputers.api.domain.platform.application.usecase.transfer.UpdatePlatformTransferUseCase;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.platform.domain.dto.UpdatePlatformTransferDto;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdatePlatformTransferService implements UpdatePlatformTransferUseCase {
    private final PlatformTransferRepository repository;
    private final PlatformRepository platformRepository;
    private final GetPlatformTransferUseCase getPlatformTransferUseCase;

    /**
     * Update an existing and available transfer.
     *
     * @param transferId                the ID to be searched.
     * @param updatePlatformTransferDto the data to be updated.
     * @return an {@link Optional} of the transfer updated.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferResponse call(Integer transferId, UpdatePlatformTransferDto updatePlatformTransferDto) {
        final var transfer = getPlatformTransferUseCase.call(transferId);

        if (updatePlatformTransferDto.platformId() != null) {
            final var platform = platformRepository.get(updatePlatformTransferDto.platformId())
                    .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + updatePlatformTransferDto.platformId()));

            transfer.setPlatform(platform);
        }

        transfer.setValue(updatePlatformTransferDto.value() != null ? updatePlatformTransferDto.value() : transfer.getValue());

        return repository.save(transfer);
    }
}
