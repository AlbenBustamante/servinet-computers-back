package com.servinetcomputers.api.module.safes.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.module.base.BaseDto;
import com.servinetcomputers.api.module.safes.application.usecase.detail.LoadSafeDetailsOfDayUseCase;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadSafeDetailsOfDayService implements LoadSafeDetailsOfDayUseCase {
    private final SafeDetailRepository repository;
    private final SafeRepository safeRepository;
    private final SafeBaseRepository safeBaseRepository;
    private final DateTimeService dateTimeService;

    /**
     * Do a safe details search of the current day and return it if found data.
     * <p>Otherwise, search the last registered base by the safe ID and create a new detail for the day.</p>
     * <p>And finally, if not found any base, sets a zero base and create a new detail for the day.</p>
     *
     * @return the details of the day.
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public List<SafeDetailDto> call() {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var details = repository.getAllByDateBetween(startDate, endDate);

        if (!details.isEmpty()) {
            return details;
        }

        final var safes = safeRepository.getAll();
        final List<SafeDetailDto> newDetails = new ArrayList<>(safes.size());

        safes.forEach(safe -> {
            final var lastBase = safeBaseRepository.getLastBySafeId(safe.getId());
            final var base = lastBase.isPresent() ? lastBase.get().getDetailBase() : BaseDto.zero();

            final var createSafeDetailDto = new CreateSafeDetailDto(safe.getId(), base, base, safe);
            final var newSafeDetailDto = repository.save(createSafeDetailDto);
            final var createSafeBaseDto = new CreateSafeBaseDto(newSafeDetailDto.getId(), base, newSafeDetailDto);

            safeBaseRepository.save(createSafeBaseDto);

            newDetails.add(newSafeDetailDto);
        });

        return newDetails;
    }
}
