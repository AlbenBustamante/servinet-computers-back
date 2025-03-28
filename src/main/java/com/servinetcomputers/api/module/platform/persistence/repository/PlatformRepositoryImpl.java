package com.servinetcomputers.api.module.platform.persistence.repository;

import com.servinetcomputers.api.module.platform.domain.dto.PlatformRequest;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformResponse;
import com.servinetcomputers.api.module.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.module.platform.persistence.JpaPlatformRepository;
import com.servinetcomputers.api.module.platform.persistence.mapper.PlatformMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The platform's repository implementation.
 */
@RequiredArgsConstructor
@Repository
public class PlatformRepositoryImpl implements PlatformRepository {
    private final JpaPlatformRepository repository;
    private final PlatformMapper mapper;

    @Override
    public boolean existsByName(String name) {
        return repository.existsByNameAndEnabledTrue(name);
    }

    @Override
    public PlatformResponse save(PlatformRequest request) {
        final var entity = mapper.toEntity(request);
        final var newPlatform = repository.save(entity);

        return mapper.toResponse(newPlatform);
    }

    @Override
    public PlatformResponse save(PlatformResponse response) {
        final var entity = mapper.toEntity(response);
        final var newPlatform = repository.save(entity);

        return mapper.toResponse(newPlatform);
    }

    @Override
    public List<PlatformResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }

    @Override
    public Optional<PlatformResponse> get(int id) {
        final var platform = repository.findByIdAndEnabledTrue(id);
        return platform.map(mapper::toResponse);
    }
}
