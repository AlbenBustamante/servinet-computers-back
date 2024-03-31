package com.servinetcomputers.api.domain.platform;

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

import static com.servinetcomputers.api.security.util.SecurityConstants.ADMIN_AUTHORITY;
import static com.servinetcomputers.api.security.util.SecurityConstants.CASHIER_AUTHORITY;

/**
 * The platform's service implementation.
 */
@RequiredArgsConstructor
@Service
public class PlatformServiceImpl implements IPlatformService {

    private final PlatformRepository repository;
    private final PlatformMapper mapper;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public PlatformResponse create(PlatformRequest request) {
        if (repository.findByName(request.name()).isPresent()) {
            throw new BadRequestException("La plataforma ya existe");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlatformResponse> getAll() {
        return mapper.toResponses(repository.findAllByIsAvailable(true));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public PlatformResponse update(int platformId, PlatformRequest request) {
        final var platform = repository.findById(platformId)
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: " + platformId));

        if (platform.getIsAvailable().equals(Boolean.FALSE)) {
            throw new NotFoundException("Plataforma no encontrada: " + platformId);
        }

        platform.setName(request.name() == null ? platform.getName() : request.name());

        return mapper.toResponse(repository.save(platform));
    }

    @Secured(value = CASHIER_AUTHORITY)
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
