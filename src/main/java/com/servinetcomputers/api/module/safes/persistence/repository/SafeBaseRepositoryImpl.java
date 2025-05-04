package com.servinetcomputers.api.module.safes.persistence.repository;

import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeBaseDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeBaseRepository;
import com.servinetcomputers.api.module.safes.persistence.JpaSafeBaseRepository;
import com.servinetcomputers.api.module.safes.persistence.mapper.SafeBaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SafeBaseRepositoryImpl implements SafeBaseRepository {
    private final JpaSafeBaseRepository repository;
    private final SafeBaseMapper mapper;

    @Override
    public SafeBaseDto save(CreateSafeBaseDto request) {
        final var entity = mapper.toEntity(request);
        final var newSafeBase = repository.save(entity);

        return mapper.toDto(newSafeBase);
    }

    @Override
    public Optional<SafeBaseDto> getLastBySafeId(int safeId) {
        final var base = repository.findFirstBySafeDetailSafeIdAndEnabledTrueOrderByCreatedDateDesc(safeId);
        return base.map(mapper::toDto);
    }

    @Override
    public List<SafeBaseDto> getAllBySafeDetailId(int safeDetailId) {
        final var bases = repository.findAllByEnabledTrueAndSafeDetailId(safeDetailId);
        return mapper.toDto(bases);
    }
}
