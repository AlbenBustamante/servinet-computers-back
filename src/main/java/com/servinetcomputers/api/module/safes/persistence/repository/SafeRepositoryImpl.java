package com.servinetcomputers.api.module.safes.persistence.repository;

import com.servinetcomputers.api.module.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import com.servinetcomputers.api.module.safes.persistence.JpaSafeRepository;
import com.servinetcomputers.api.module.safes.persistence.mapper.SafeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SafeRepositoryImpl implements SafeRepository {
    private final JpaSafeRepository repository;
    private final SafeMapper mapper;

    @Override
    public SafeResponse save(SafeRequest request) {
        final var entity = mapper.toEntity(request);
        final var newSafe = repository.save(entity);

        return mapper.toResponse(newSafe);
    }

    @Override
    public SafeResponse save(SafeResponse response) {
        final var entity = mapper.toEntity(response);
        final var newSafe = repository.save(entity);

        return mapper.toResponse(newSafe);
    }

    @Override
    public boolean existsByNumeral(int numeral) {
        return repository.existsByNumeralAndEnabledTrue(numeral);
    }

    @Override
    public Optional<SafeResponse> get(int safeId) {
        final var safe = repository.findByIdAndEnabledTrue(safeId);
        return safe.map(mapper::toResponse);
    }

    @Override
    public List<SafeResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }
}
