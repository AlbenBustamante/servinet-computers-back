package com.servinetcomputers.api.module.safes.persistence.repository;

import com.servinetcomputers.api.module.safes.domain.dto.SafeRequest;
import com.servinetcomputers.api.module.safes.domain.dto.SafeResponse;
import com.servinetcomputers.api.module.safes.domain.repository.SafeRepository;
import com.servinetcomputers.api.module.safes.persistence.JpaSafeRepository;
import com.servinetcomputers.api.module.safes.persistence.mapper.SafeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public boolean existsByNumeral(int numeral) {
        return repository.existsByNumeralAndEnabledTrue(numeral);
    }

    @Override
    public List<SafeResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }
}
