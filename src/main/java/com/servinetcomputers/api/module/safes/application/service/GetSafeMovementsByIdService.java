package com.servinetcomputers.api.module.safes.application.service;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.safes.application.port.out.LoadDetailsBySafeIdAndBetweenPort;
import com.servinetcomputers.api.module.safes.application.usecase.GetSafeMovementsByIdUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import com.servinetcomputers.api.module.safes.exception.SafeDetailBySafeIdNotFoundException;
import com.servinetcomputers.api.module.safes.exception.SafeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.servinetcomputers.api.core.util.constants.SecurityConstants.ADMIN_AUTHORITY;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class GetSafeMovementsByIdService implements GetSafeMovementsByIdUseCase {
    private final LoadDetailsBySafeIdAndBetweenPort loadDetailsBySafeIdAndBetweenPort;
    private final SafeDetailRepository safeDetailRepository;
    private final SafeBaseRepository safeBaseRepository;
    private final SafeRepository safeRepository;
    private final DateTimeService dateTimeService;

    @Override
    @Secured(value = ADMIN_AUTHORITY)
    public SafeDetailDto call(Integer safeId, LocalDate date) {
        final var safe = safeRepository.get(safeId)
                .orElseThrow(() -> new SafeNotFoundException(safeId));

        final var startDate = date.atStartOfDay();
        final var endDate = date.plusDays(1).atStartOfDay();
        final var details = loadDetailsBySafeIdAndBetweenPort.load(safe.getId(), startDate, endDate);

        if (!details.isEmpty()) {
            return details.get(0);
        }

        final var today = dateTimeService.dateNow();

        if (date != today) {
            throw new SafeDetailBySafeIdNotFoundException(safeId);
        }

        final var lastBase = safeBaseRepository.getLastBySafeId(safeId);
        final var newBase = lastBase.isPresent() ? lastBase.get().getDetailBase() : BaseDto.zero();

        final var createDetailDto = new CreateSafeDetailDto(safeId, newBase, newBase, safe);
        final var newDetail = safeDetailRepository.save(createDetailDto);
        final var createBaseDto = new CreateSafeBaseDto(newDetail.getId(), newBase, newDetail);
        final var newSafeBase = safeBaseRepository.save(createBaseDto);
        newDetail.getBases().add(newSafeBase);

        return newDetail;
    }
}
