package com.servinetcomputers.api.module.safes.application.service.detail;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.module.safes.persistence.entity.SafeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateSafeDetailBaseService implements UpdateSafeDetailBaseUseCase {
    private final SafeDetailRepository repository;
    private final SafeBaseRepository safeBaseRepository;

    /**
     * If the initial base is by default, then update the initial and final base.
     * <p>If not, then update only the final base.</p>
     * <p>Also, create a new {@link SafeBase} record.</p>
     *
     * @param baseDto a {@link BaseDto} model dto.
     * @return a {@link SafeDetailDto} model dto.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public SafeDetailDto call(Integer safeDetailId, BaseDto baseDto) {
        final var safeDetail = repository.get(safeDetailId)
                .orElseThrow(() -> new NotFoundException("Caja del día no encontrada: #" + safeDetailId));

        if (safeDetail.getDetailInitialBase().equals(BaseDto.zero())) {
            safeDetail.setDetailInitialBase(baseDto);
        }

        safeDetail.setDetailFinalBase(baseDto);

        final var newSafeDetailDto = repository.save(safeDetail);
        final var createSafeBaseDto = new CreateSafeBaseDto(safeDetailId, baseDto, newSafeDetailDto);
        final var newSafeBase = safeBaseRepository.save(createSafeBaseDto);
        newSafeDetailDto.getBases().add(newSafeBase);

        return newSafeDetailDto;
    }
}
