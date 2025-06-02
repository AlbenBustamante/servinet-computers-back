package com.servinetcomputers.api.module.safes.application.service.detail;

import com.servinetcomputers.api.core.common.UseCase;
import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.module.safes.application.usecase.GetSafeMovementsByIdUseCase;
import com.servinetcomputers.api.module.safes.application.usecase.detail.LoadSafeDetailsOfDayUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LoadSafeDetailsOfDayService implements LoadSafeDetailsOfDayUseCase {
    private final SafeRepository safeRepository;
    private final DateTimeService dateTimeService;
    private final GetSafeMovementsByIdUseCase getSafeMovementsByIdUseCase;

    @Override
    public List<SafeDetailDto> call() {
        final var today = dateTimeService.dateNow();
        final var safes = safeRepository.getAll();
        final List<SafeDetailDto> newDetails = new ArrayList<>(safes.size());

        safes.forEach(safe -> {
            final var detail = getSafeMovementsByIdUseCase.call(safe.getId(), today);
            newDetails.add(detail);
        });

        return newDetails;
    }
}
