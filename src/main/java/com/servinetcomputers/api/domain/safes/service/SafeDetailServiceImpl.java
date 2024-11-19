package com.servinetcomputers.api.domain.safes.service;

import com.servinetcomputers.api.domain.safes.abs.ISafeDetailService;
import com.servinetcomputers.api.domain.safes.abs.SafeDetailMapper;
import com.servinetcomputers.api.domain.safes.abs.SafeDetailRepository;
import com.servinetcomputers.api.domain.safes.abs.SafeRepository;
import com.servinetcomputers.api.domain.safes.dto.SafeDetailRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeDetailResponse;
import com.servinetcomputers.api.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SafeDetailServiceImpl implements ISafeDetailService {

    private final SafeDetailRepository repository;
    private final SafeDetailMapper mapper;
    private final SafeRepository safeRepository;

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

        for (final var safe : safes) {
            final var detail = new SafeDetailRequest(safe.getId(), null, null);
            final var entity = mapper.toEntity(detail);
            entity.setSafe(safe);

            final var result = repository.save(entity);

            newDetails.add(mapper.toResponse(result));
        }

        return newDetails;
    }

}
