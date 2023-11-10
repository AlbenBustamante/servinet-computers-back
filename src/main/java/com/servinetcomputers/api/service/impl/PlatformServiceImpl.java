package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.PlatformRequest;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.PlatformResponse;
import com.servinetcomputers.api.exception.PlatformAlreadyExistsException;
import com.servinetcomputers.api.exception.PlatformNotFoundException;
import com.servinetcomputers.api.exception.PlatformUnavailableException;
import com.servinetcomputers.api.mapper.PlatformMapper;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.service.IPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The platform's service implementation.
 */
@RequiredArgsConstructor
@Service
public class PlatformServiceImpl implements IPlatformService {

    private final PlatformRepository repository;
    private final PlatformMapper mapper;

    @Override
    public PageResponse<PlatformResponse> create(PlatformRequest request) {
        if (repository.findByName(request.name()).isPresent()) {
            throw new PlatformAlreadyExistsException("name");
        }

        final var response = mapper.toResponse(repository.save(mapper.toEntity(request)));

        return new PageResponse<>(201, true, new DataResponse<>(1, 1, List.of(response)));
    }

    @Override
    public PageResponse<PlatformResponse> update(int platformId, PlatformRequest request) {
        final var platformFound = repository.findById(platformId);

        if (platformFound.isEmpty()) {
            throw new PlatformNotFoundException(platformId);
        }

        final var platform = platformFound.get();

        if (Boolean.FALSE.equals(platform.getIsAvailable())) {
            throw new PlatformUnavailableException(platformId);
        }

        platform.setName(request.name() == null ? platform.getName() : request.name());

        final var response = mapper.toResponse(repository.save(platform));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, List.of(response)));
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
