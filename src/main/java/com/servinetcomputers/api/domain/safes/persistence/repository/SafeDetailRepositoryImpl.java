package com.servinetcomputers.api.domain.safes.persistence.repository;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.domain.safes.persistence.JpaSafeDetailRepository;
import com.servinetcomputers.api.domain.safes.persistence.JpaSafeRepository;
import com.servinetcomputers.api.domain.safes.persistence.entity.SafeDetail;
import com.servinetcomputers.api.domain.safes.persistence.mapper.SafeDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SafeDetailRepositoryImpl implements SafeDetailRepository {
    private final JpaSafeDetailRepository repository;
    private final SafeDetailMapper mapper;
    private final JpaSafeRepository jpaSafeRepository;

    @Override
    public SafeDetailResponse save(SafeDetailResponse response) {
        final var entity = mapper.toEntity(response);
        final var newSafeDetail = repository.save(entity);

        return mapper.toResponse(newSafeDetail);
    }

    @Override
    public Optional<SafeDetailResponse> get(int id) {
        final var safeDetail = repository.findByIdAndEnabledTrue(id);
        return safeDetail.map(mapper::toResponse);
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public List<SafeDetailResponse> loadSafes() {
        final var today = LocalDate.now();
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now());

        final var details = repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);

        if (!details.isEmpty()) {
            return mapper.toResponses(details);
        }

        final var safes = jpaSafeRepository.findAllByEnabledTrue();
        final var newDetails = new ArrayList<SafeDetailResponse>(safes.size());

        final var yesterdayStartDate = startDate.minusDays(1);
        final var yesterdayEndDate = endDate.minusDays(1);

        final var yesterdayDetails = repository.findAllByEnabledTrueAndCreatedDateBetween(yesterdayStartDate, yesterdayEndDate);

        for (final var safe : safes) {
            final var detail = new SafeDetail();

            if (!yesterdayDetails.isEmpty()) {
                final var safeFound = yesterdayDetails
                        .stream()
                        .filter(d -> d.getSafe().getNumeral().equals(safe.getNumeral()))
                        .findFirst();

                if (safeFound.isEmpty()) continue;

                final var finalBase = safeFound.get().getFinalBase();

                detail.setInitialBase(finalBase);
                detail.setFinalBase(finalBase);
            }

            detail.setSafe(safe);

            final var newDetail = repository.save(detail);

            newDetails.add(mapper.toResponse(newDetail));
        }

        return newDetails;
    }
}
