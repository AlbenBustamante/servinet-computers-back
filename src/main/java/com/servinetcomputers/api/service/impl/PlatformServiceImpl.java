package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.PlatformResponse;
import com.servinetcomputers.api.mapper.PlatformMapper;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.service.IPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The platform's service implementation.
 */
@RequiredArgsConstructor
@Service
public class PlatformServiceImpl implements IPlatformService {

    private final PlatformRepository repository;
    private final PlatformMapper mapper;

    @Override
    public PlatformResponse create(PlatformRequest request) {
        if (repository.findByName(request.name()).isPresent()) {
            throw new RuntimeException("The platform already exists");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public Optional<PlatformResponse> update(int platformId, PlatformRequest request) {
        final var platformFound = repository.findById(platformId);

        if (platformFound.isEmpty()) {
            return Optional.empty();
        }

        final var platform = platformFound.get();

        if (!platform.getIsAvailable()) {
            throw new RuntimeException("The platform is disabled");
        }

        platform.setName(request.name() == null ? platform.getName() : request.name());

        return Optional.of(mapper.toResponse(repository.save(platform)));
    }

    @Override
    public boolean delete(int platformId) {
        final var platformFound = repository.findById(platformId);

        if (platformFound.isEmpty()) {
            return false;
        }

        platformFound.get().setIsAvailable(false);

        repository.save(platformFound.get());

        return true;
    }

}
