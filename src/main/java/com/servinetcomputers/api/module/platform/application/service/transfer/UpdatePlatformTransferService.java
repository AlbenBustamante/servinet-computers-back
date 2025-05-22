package com.servinetcomputers.api.module.platform.application.service.transfer;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.GetPlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.UpdatePlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformTransferRepository;
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
     * @param transferId the ID to be searched.
     * @param dto        the data to be updated.
     * @return an {@link Optional} of the transfer updated.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public PlatformTransferDto call(Integer transferId, UpdatePlatformTransferDto dto) {
        final var transfer = getPlatformTransferUseCase.call(transferId);

        if (dto.platformId() != null) {
            final var platform = platformRepository.get(dto.platformId())
                    .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: #" + dto.platformId()));

            transfer.setPlatform(platform);
        }

        transfer.setValue(dto.value() != null ? dto.value() : transfer.getValue());

        return repository.save(transfer);
    }
}
