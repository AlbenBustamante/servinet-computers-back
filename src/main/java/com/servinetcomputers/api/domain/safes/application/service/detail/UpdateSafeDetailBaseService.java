package com.servinetcomputers.api.domain.safes.application.service.detail;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.safes.application.usecase.detail.UpdateSafeDetailBaseUseCase;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.domain.safes.persistence.entity.SafeBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.servinetcomputers.api.domain.safes.util.SafeConstants.DEFAULT_BASE;

@RequiredArgsConstructor
@Service
public class UpdateSafeDetailBaseService implements UpdateSafeDetailBaseUseCase {
    private final SafeDetailRepository repository;
    private final SafeBaseRepository safeBaseRepository;
    private final BaseMapper baseMapper;

    /**
     * If the initial base is by default, then update the initial and final base.
     * <p>If not, then update only the final base.</p>
     * <p>Also, create a new {@link SafeBase} record.</p>
     *
     * @param baseDto a {@link BaseDto} model dto.
     * @return a {@link SafeDetailResponse} model dto.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public SafeDetailResponse call(Integer safeDetailId, BaseDto baseDto) {
        final var safeDetail = repository.get(safeDetailId)
                .orElseThrow(() -> new NotFoundException("Caja del día no encontrada: #" + safeDetailId));

        final var initialBaseStr = baseMapper.toStr(safeDetail.getDetailInitialBase());

        if (initialBaseStr.equals(DEFAULT_BASE)) {
            safeDetail.setDetailInitialBase(baseDto);
        }

        safeDetail.setDetailFinalBase(baseDto);
        final var newSafeDetail = repository.save(safeDetail);

        final var safeBase = new SafeBaseRequest(safeDetailId, baseDto);
        safeBase.setSafeDetail(newSafeDetail);

        safeBaseRepository.save(safeBase);

        return newSafeDetail;
    }
}
