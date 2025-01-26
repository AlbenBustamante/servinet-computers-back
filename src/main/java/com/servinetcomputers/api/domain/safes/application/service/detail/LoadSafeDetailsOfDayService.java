package com.servinetcomputers.api.domain.safes.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.base.BaseDto;
import com.servinetcomputers.api.domain.safes.application.usecase.detail.LoadSafesOfDayUseCase;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadSafeDetailsOfDayService implements LoadSafesOfDayUseCase {
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
    public List<SafeDetailResponse> call() {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        final var details = repository.getAllByDateBetween(startDate, endDate);

        if (!details.isEmpty()) {
            return details;
        }

        final var safeIds = safeRepository.getAllIds();
        final List<SafeDetailResponse> newDetails = new ArrayList<>(safeIds.size());

        safeIds.forEach(safeId -> {
            final var lastBase = safeBaseRepository.getLastBySafeId(safeId);
            final var base = lastBase.isPresent() ? lastBase.get().getDetailBase() : BaseDto.zero();

            final var newDetailRequest = new SafeDetailRequest(safeId, base, base);
            final var newDetailResponse = repository.save(newDetailRequest);

            final var safeBase = new SafeBaseRequest(newDetailResponse.getId(), base);
            safeBase.setSafeDetail(newDetailResponse);
            safeBaseRepository.save(safeBase);

            newDetails.add(newDetailResponse);
        });

        return newDetails;
    }
}
