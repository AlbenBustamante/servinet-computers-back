package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.request.CampusRequest;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.mapper.CampusMapper;
import com.servinetcomputers.api.repository.CampusRepository;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.service.ICampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * The campus' service implementation.
 */
@RequiredArgsConstructor
@Service
public class CampusServiceImpl implements ICampusService {

    private final CampusRepository repository;
    private final CampusMapper mapper;
    private final UserRepository userRepository;


    @Override
    public CampusResponse create(CampusRequest request) {
        if (repository.existsByAddress(request.address())) {
            throw new RuntimeException("The address already exists.");
        }

        if (repository.existsByCellphone(request.cellphone())) {
            throw new RuntimeException("The cellphone already exists.");
        }

        if (repository.existsByNumeral(request.numeral())) {
            throw new RuntimeException("The numeral already exists.");
        }

        if (!request.passwordsMatch()) {
            throw new RuntimeException("The passwords don't match.");
        }

        final var entity = mapper.toEntity(request);

        while (entity.getTerminal() == null || repository.existsByTerminal(entity.getTerminal())) {
            entity.setTerminal(String.valueOf(new Random().nextInt(100, 500)));
        }

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public Optional<CampusResponse> get(int campusId) {
        final var campusFound = repository.findById(campusId);

        if (campusFound.isEmpty() || !campusFound.get().getIsAvailable()) {
            return Optional.empty();
        }

        return campusFound.map(mapper::toResponse);
    }

    @Override
    public Optional<List<CampusResponse>> getAllByUserId(int userId) {
        final var userFound = userRepository.findById(userId);

        if (userFound.isEmpty() || !userFound.get().getIsAvailable()) {
            return Optional.empty();
        }

        return Optional.of(mapper.toResponses(userFound.get().getCampuses()));
    }

    @Override
    public Optional<CampusResponse> update(int campusId, CampusRequest request) {
        final var campusFound = repository.findById(campusId);

        if (campusFound.isEmpty()) {
            return Optional.empty();
        }

        final var campus = campusFound.get();

        campus.setAddress(request.address() == null ? campus.getAddress() : request.address());
        campus.setCellphone(request.cellphone() == null ? campus.getCellphone() : request.cellphone());

        return Optional.of(mapper.toResponse(repository.save(campus)));
    }

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

}
