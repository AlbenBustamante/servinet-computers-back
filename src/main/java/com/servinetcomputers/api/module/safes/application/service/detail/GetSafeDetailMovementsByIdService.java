package com.servinetcomputers.api.module.safes.application.service.detail;

import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.core.util.constants.SecurityConstants;
import com.servinetcomputers.api.module.safes.application.usecase.detail.GetSafeDetailMovementsByIdUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeMovementDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetSafeDetailMovementsByIdService implements GetSafeDetailMovementsByIdUseCase {
    private final SafeDetailRepository safeDetailRepository;
    private final SafeBaseRepository safeBaseRepository;

    @Secured(value = SecurityConstants.ADMIN_AUTHORITY)
    @Transactional(readOnly = true)
    @Override
    public SafeMovementDto call(Integer safeDetailId) {
        final var detail = safeDetailRepository.get(safeDetailId)
                .orElseThrow(() -> new NotFoundException("No se encontraron los movimientos de la caja solicitada: #" + safeDetailId));

        final var bases = safeBaseRepository.getAllBySafeDetailId(safeDetailId);

        return new SafeMovementDto(detail, bases);
    }
}
