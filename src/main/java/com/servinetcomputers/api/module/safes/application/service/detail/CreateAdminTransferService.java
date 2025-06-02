package com.servinetcomputers.api.module.safes.application.service.detail;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.module.safes.application.port.in.CreateAdminTransferUseCase;
import com.servinetcomputers.api.module.safes.application.port.in.command.CreateAdminTransferCommand;
import com.servinetcomputers.api.module.safes.application.port.out.LoadSafeDetailByIdPort;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CreateAdminTransferService implements CreateAdminTransferUseCase {
    private final LoadSafeDetailByIdPort loadByIdPort;
    private final SafeDetailRepository safeDetailRepository;
    private final SafeBaseRepository safeBaseRepository;

    @Override
    public SafeDetailDto create(Integer safeDetailId, CreateAdminTransferCommand command) {
        final var safeDetail = loadByIdPort.load(safeDetailId);
        final var base = safeDetail.getDetailFinalBase();
        base.addOrSubtract(command.amount(), command.denomination(), command.add());

        final var updatedSafeDetail = safeDetailRepository.save(safeDetail);
        final var createSafeBase = new CreateSafeBaseDto(safeDetailId, base, updatedSafeDetail);
        final var newSafeBase = safeBaseRepository.save(createSafeBase);
        updatedSafeDetail.getBases().add(newSafeBase);

        return updatedSafeDetail;
    }
}
