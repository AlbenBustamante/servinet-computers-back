package com.servinetcomputers.api.module.safes.application.service;

import com.servinetcomputers.api.core.util.constants.SecurityConstants;
import com.servinetcomputers.api.module.safes.application.usecase.GetAllSafeMovementsUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeMovementDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllSafeMovementsService implements GetAllSafeMovementsUseCase {
    private final SafeDetailRepository safeDetailRepository;
    private final SafeBaseRepository safeBaseRepository;

    @Secured(value = SecurityConstants.ADMIN_AUTHORITY)
    @Transactional(readOnly = true)
    @Override
    public List<SafeMovementDto> call(Integer safeId) {
        final var details = safeDetailRepository.getAllBySafeId(safeId);
        final List<SafeMovementDto> movements = new ArrayList<>(details.size());

        for (final var detail : details) {
            final var bases = safeBaseRepository.getAllBySafeDetailId(detail.getId());
            final var movement = new SafeMovementDto(detail, bases);
            movements.add(movement);
        }

        return movements;
    }
}
