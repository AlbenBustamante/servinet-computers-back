package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.dto.response.DataResponse;
import com.servinetcomputers.api.dto.response.ModelResponse;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.exception.AppException;
import com.servinetcomputers.api.exception.CampusAlreadyExistsException;
import com.servinetcomputers.api.exception.CampusNotFoundException;
import com.servinetcomputers.api.exception.CampusUnavailableException;
import com.servinetcomputers.api.exception.PasswordsDoNotMatchException;
import com.servinetcomputers.api.exception.PlatformNameNotFoundException;
import com.servinetcomputers.api.exception.UserNotFoundException;
import com.servinetcomputers.api.exception.UserUnavailableException;
import com.servinetcomputers.api.mapper.CampusMapper;
import com.servinetcomputers.api.model.Campus;
import com.servinetcomputers.api.model.Platform;
import com.servinetcomputers.api.repository.CampusRepository;
import com.servinetcomputers.api.repository.PlatformRepository;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.service.ICampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.servinetcomputers.api.util.constants.SecurityConstants.CAMPUS_AUTHORITY;
import static com.servinetcomputers.api.util.constants.SecurityConstants.USER_AUTHORITY;

/**
 * The campus' service implementation.
 */
@RequiredArgsConstructor
@Service
public class CampusServiceImpl implements ICampusService {

    private final CampusRepository repository;
    private final CampusMapper mapper;
    private final UserRepository userRepository;
    private final PlatformRepository platformRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<CampusResponse> create(CampusRequest request) {
        if (repository.existsByAddress(request.address())) {
            throw new CampusAlreadyExistsException("address");
        }

        if (repository.existsByCellphone(request.cellphone())) {
            throw new CampusAlreadyExistsException("cellphone");
        }

        if (repository.existsByNumeralAndUserId(request.numeral(), request.userId())) {
            throw new CampusAlreadyExistsException(request.numeral(), request.userId());
        }

        if (!request.passwordsMatch()) {
            throw new PasswordsDoNotMatchException();
        }

        final var entity = mapper.toEntity(request);

        while (entity.getTerminal() == null || repository.existsByTerminal(entity.getTerminal())) {
            final var min = 1000;
            final var max = 1999;
            final var range = max - min + 1;
            final var rnd = (int) (Math.random() * range) + min;

            entity.setTerminal(String.valueOf(rnd));
        }

        entity.setPassword(passwordEncoder.encode(request.password()));

        final var response = mapper.toResponse(repository.save(entity));

        return new PageResponse<>(201, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Transactional(readOnly = true)
    @Secured(value = {USER_AUTHORITY, CAMPUS_AUTHORITY})
    @Override
    public PageResponse<CampusResponse> get(int campusId) {
        final var campus = repository.findById(campusId)
                .orElseThrow(() -> new CampusNotFoundException(campusId));

        if (campus.getIsAvailable().equals(Boolean.FALSE)) {
            throw new CampusUnavailableException(campusId);
        }

        final var response = mapper.toResponse(campus);

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Transactional(readOnly = true)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<CampusResponse> getAllByUserId(int userId) {
        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getIsAvailable().equals(Boolean.FALSE)) {
            throw new UserUnavailableException(userId);
        }

        final var response = mapper.toResponses(user.getCampuses())
                .stream()
                .filter(ModelResponse::isAvailable)
                .toList();

        return new PageResponse<>(200, true, new DataResponse<>(response.size(), 1, 1, response));
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<CampusResponse> update(int campusId, CampusRequest request) {
        final var campus = repository.findById(campusId)
                .orElseThrow(() -> new CampusNotFoundException(campusId));

        if (campus.getIsAvailable().equals(Boolean.FALSE)) {
            throw new CampusUnavailableException(campusId);
        }

        campus.setAddress(request.address() == null ? campus.getAddress() : request.address());
        campus.setCellphone(request.cellphone() == null ? campus.getCellphone() : request.cellphone());

        final var response = mapper.toResponse(repository.save(campus));

        return new PageResponse<>(200, true, new DataResponse<>(1, 1, 1, List.of(response)));
    }

    @Secured(value = USER_AUTHORITY)
    @Override
    public boolean delete(int campusId) {
        final var campusFound = repository.findById(campusId);

        if (campusFound.isEmpty()) {
            return false;
        }

        campusFound.get().setIsAvailable(false);

        repository.save(campusFound.get());

        return true;
    }

    private void addOrRemove(Campus campus, String platformName, boolean add) {
        final var platform = platformRepository.findByName(platformName)
                .orElseThrow(() -> new PlatformNameNotFoundException(platformName));

        if (add) {
            campus.addPlatform(platform);
        } else {
            campus.removePlatform(platform);
        }
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = USER_AUTHORITY)
    @Override
    public PageResponse<CampusResponse> updatePlatforms(int campusId, List<String> platformNames) {
        final var campus = repository.findById(campusId)
                .orElseThrow(() -> new CampusNotFoundException(campusId));

        final var platforms = platformRepository.findAll()
                .stream().filter(Platform::getIsAvailable)
                .toList();

        platforms.forEach(platform -> {
            final var isIncluded = platformNames.stream().anyMatch(p -> platform.getName().equals(p));

            addOrRemove(campus, platform.getName(), isIncluded);
        });

        final var response = mapper.toResponse(repository.save(campus));

        return new PageResponse<>(200, true, new DataResponse<>(response.getPlatforms().size(), 1, 1, List.of(response)));
    }
}
