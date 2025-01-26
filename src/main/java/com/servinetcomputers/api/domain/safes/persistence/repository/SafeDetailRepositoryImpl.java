package com.servinetcomputers.api.domain.safes.persistence.repository;

import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeDetailResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.domain.safes.persistence.JpaSafeDetailRepository;
import com.servinetcomputers.api.domain.safes.persistence.mapper.SafeDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SafeDetailRepositoryImpl implements SafeDetailRepository {
    private final JpaSafeDetailRepository repository;
    private final SafeDetailMapper mapper;

    @Override
    public SafeDetailResponse save(SafeDetailRequest request) {
        final var entity = mapper.toEntity(request);
        final var newSafeDetail = repository.save(entity);

        return mapper.toResponse(newSafeDetail);
    }

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

    @Override
    public List<SafeDetailResponse> getAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return mapper.toResponses(details);
    }
}
