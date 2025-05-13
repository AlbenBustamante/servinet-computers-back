package com.servinetcomputers.api.module.safes.persistence.repository;

import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDto;
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
    public SafeDto save(CreateSafeDto request) {
        final var entity = mapper.toEntity(request);
        final var newSafe = repository.save(entity);

        return mapper.toDto(newSafe);
    }

    @Override
    public SafeDto save(SafeDto response) {
        final var entity = mapper.toEntity(response);
        final var newSafe = repository.save(entity);

        return mapper.toDto(newSafe);
    }

    @Override
    public boolean existsByNumeral(int numeral) {
        return repository.existsByNumeralAndEnabledTrue(numeral);
    }

    @Override
    public Optional<SafeDto> get(int safeId) {
        final var safe = repository.findByIdAndEnabledTrue(safeId);
        return safe.map(mapper::toDto);
    }

    @Override
    public List<SafeDto> getAll() {
        return mapper.toDto(repository.findAllByEnabledTrue());
    }
}
