package com.servinetcomputers.api.module.platform.persistence.repository;

import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformDto;
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
    public PlatformDto save(CreatePlatformDto request) {
        final var entity = mapper.toEntity(request);
        final var newPlatform = repository.save(entity);

        return mapper.toDto(newPlatform);
    }

    @Override
    public PlatformDto save(PlatformDto response) {
        final var entity = mapper.toEntity(response);
        final var newPlatform = repository.save(entity);

        return mapper.toDto(newPlatform);
    }

    @Override
    public List<PlatformDto> getAll() {
        return mapper.toDto(repository.findAllByEnabledTrue());
    }

    @Override
    public Optional<PlatformDto> get(int id) {
        final var platform = repository.findByIdAndEnabledTrue(id);
        return platform.map(mapper::toDto);
    }
}
