package com.servinetcomputers.api.domain.safes.persistence.repository;

import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseRequest;
import com.servinetcomputers.api.domain.safes.domain.dto.SafeBaseResponse;
import com.servinetcomputers.api.domain.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.domain.safes.persistence.JpaSafeBaseRepository;
import com.servinetcomputers.api.domain.safes.persistence.mapper.SafeBaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SafeBaseRepositoryImpl implements SafeBaseRepository {
    private final JpaSafeBaseRepository repository;
    private final SafeBaseMapper mapper;

    @Override
    public SafeBaseResponse save(SafeBaseRequest request) {
        final var entity = mapper.toEntity(request);
        final var newSafeBase = repository.save(entity);

        return mapper.toResponse(newSafeBase);
    }

    @Override
    public Optional<SafeBaseResponse> getLastBySafeId(int safeId) {
        final var base = repository.findFirstBySafeDetailSafeIdAndEnabledTrueOrderByCreatedDateDesc(safeId);
        return base.map(mapper::toResponse);
    }
}
