package com.servinetcomputers.api.domain.safes.service;

import com.servinetcomputers.api.domain.base.BaseMapper;
import com.servinetcomputers.api.domain.safes.abs.*;
import com.servinetcomputers.api.domain.safes.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.entity.SafeDetail;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.domain.safes.util.SafeConstants.DEFAULT_BASE;

@Service
@RequiredArgsConstructor
public class SafeDetailServiceImpl implements ISafeDetailService {

    private final SafeDetailRepository repository;
    private final SafeDetailMapper mapper;
    private final SafeRepository safeRepository;
    private final SafeBaseRepository safeBaseRepository;
    private final SafeBaseMapper safeBaseMapper;
    private final BaseMapper baseMapper;

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

        final var safes = safeRepository.findAllByEnabledTrue();
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

    @Transactional(rollbackFor = AppException.class)
    @Override
    public SafeDetailResponse updateBase(SafeBaseRequest request) {
        final var safeDetail = repository.findByIdAndEnabledTrue(request.safeDetailId())
                .orElseThrow(() -> new NotFoundException("Caja fuerte no encontrada: #" + request.safeDetailId()));

        final var base = baseMapper.toStr(request.base());

        if (safeDetail.getInitialBase().equals(DEFAULT_BASE)) {
            safeDetail.setInitialBase(base);
        }

        safeDetail.setFinalBase(base);

        final var safeDetailUpdated = repository.save(safeDetail);

        final var baseEntity = safeBaseMapper.toEntity(request);
        baseEntity.setSafeDetail(safeDetailUpdated);

        safeBaseRepository.save(baseEntity);

        return mapper.toResponse(safeDetailUpdated);
    }

}
