package com.servinetcomputers.api.domain.platform;

import com.servinetcomputers.api.domain.DataResponse;
import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.platform.abs.IPlatformService;
import com.servinetcomputers.api.domain.platform.abs.PlatformMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformRepository;
import com.servinetcomputers.api.domain.platform.model.dto.PlatformRequest;
import com.servinetcomputers.api.domain.platform.model.dto.PlatformResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.BadRequestException;
import com.servinetcomputers.api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.security.util.SecurityConstants.USER_AUTHORITY;

/**
 * The platform's service implementation.
 */
@RequiredArgsConstructor
@Service
public class PlatformServiceImpl implements IPlatformService {

    private final PlatformRepository repository;
    private final PlatformMapper mapper;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<PlatformResponse> create(PlatformRequest request) {
        if (repository.findByName(request.name()).isPresent()) {
            throw new BadRequestException("La plataforma ya existe");
        }

        final var response = mapper.toResponse(repository.save(mapper.toEntity(request)));

        return new PageResponse<>(201, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Transactional(readOnly = true)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<PlatformResponse> getAll() {
        final var response = mapper.toResponses(repository.findAllByIsAvailable(true));
        return new PageResponse<>(200, true, new DataResponse<>(response.size(), 1, 1, response));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<PlatformResponse> update(int platformId, PlatformRequest request) {
        final var platform = repository.findById(platformId)
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: " + platformId));

        if (platform.getIsAvailable().equals(Boolean.FALSE)) {
            throw new NotFoundException("Plataforma no encontrada: " + platformId);
        }

        platform.setName(request.name() == null ? platform.getName() : request.name());

        final var response = mapper.toResponse(repository.save(platform));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Secured(value = USER_AUTHORITY)
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
