package com.servinetcomputers.api.module.safes.persistence.repository;

import com.servinetcomputers.api.module.safes.application.port.out.LoadDetailsBySafeIdAndBetweenPort;
import com.servinetcomputers.api.module.safes.application.port.out.LoadSafeDetailByIdPort;
import com.servinetcomputers.api.module.safes.domain.dto.CreateSafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.dto.SafeDetailDto;
import com.servinetcomputers.api.module.safes.domain.repository.SafeDetailRepository;
import com.servinetcomputers.api.module.safes.exception.SafeDetailNotFoundException;
import com.servinetcomputers.api.module.safes.persistence.JpaSafeDetailRepository;
import com.servinetcomputers.api.module.safes.persistence.mapper.SafeDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SafeDetailRepositoryImpl implements SafeDetailRepository, LoadDetailsBySafeIdAndBetweenPort, LoadSafeDetailByIdPort {
    private final JpaSafeDetailRepository repository;
    private final SafeDetailMapper mapper;

    @Override
    public SafeDetailDto save(CreateSafeDetailDto request) {
        final var entity = mapper.toEntity(request);
        final var newSafeDetail = repository.save(entity);

        return mapper.toDto(newSafeDetail);
    }

    @Override
    public SafeDetailDto save(SafeDetailDto response) {
        final var entity = mapper.toEntity(response);
        final var newSafeDetail = repository.save(entity);

        return mapper.toDto(newSafeDetail);
    }

    @Override
    public Optional<SafeDetailDto> get(int id) {
        final var safeDetail = repository.findByIdAndEnabledTrue(id);
        return safeDetail.map(mapper::toDto);
    }

    @Override
    public List<SafeDetailDto> getAllBySafeId(int safeId) {
        final var details = repository.findAllByEnabledTrueAndSafeId(safeId);
        return mapper.toDto(details);
    }

    @Override
    public List<SafeDetailDto> getAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllByEnabledTrueAndCreatedDateBetween(startDate, endDate);
        return mapper.toDto(details);
    }

    @Override
    public Optional<Integer> getNumeralById(int id) {
        return repository.findNumeralById(id);
    }

    @Override
    public List<SafeDetailDto> load(Integer safeId, LocalDateTime startDate, LocalDateTime endDate) {
        final var details = repository.findAllBySafeIdAndEnabledTrueAndCreatedDateBetween(safeId, startDate, endDate);
        return mapper.toDto(details);
    }

    @Override
    public SafeDetailDto load(Integer safeDetailId) {
        final var detail = repository.findByIdAndEnabledTrue(safeDetailId)
                .orElseThrow(() -> new SafeDetailNotFoundException(safeDetailId));

        return mapper.toDto(detail);
    }
}
