package com.servinetcomputers.api.domain.platform.persistence.repository;

import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.platform.domain.dto.*;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformBalanceRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformRepository;
import com.servinetcomputers.api.domain.platform.persistence.JpaPlatformTransferRepository;
import com.servinetcomputers.api.domain.platform.persistence.mapper.PlatformBalanceMapper;
import com.servinetcomputers.api.domain.platform.persistence.mapper.PlatformMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.core.security.util.SecurityConstants.ADMIN_AUTHORITY;

/**
 * The platform's repository implementation.
 */
@RequiredArgsConstructor
@Repository
public class PlatformRepositoryImpl implements PlatformRepository {
    private final JpaPlatformRepository repository;
    private final PlatformMapper mapper;
    private final JpaPlatformBalanceRepository balanceRepository;
    private final PlatformBalanceMapper balanceMapper;
    private final JpaPlatformTransferRepository transferRepository;

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public PlatformResponse create(PlatformRequest request) {
        if (repository.existsByName(request.name())) {
            throw new BadRequestException("Ya existe la plataforma");
        }

        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlatformResponse> getAll() {
        return mapper.toResponses(repository.findAllByEnabledTrue());
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public List<PortalPlatformDto> loadPortalPlatforms() {
        final var platforms = repository.findAllByEnabledTrue();

        if (platforms.isEmpty()) {
            return List.of();
        }

        final List<PortalPlatformDto> platformReports = new ArrayList<>();

        final var initialDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        final var finalDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        platforms.forEach(platform -> {
            final var balance = getPlatformBalance(platform.getId(), initialDate, finalDate);
            final var transfersAmount = getPlatformTransfersAmount(platform.getId(), initialDate, finalDate);
            final var transfersTotal = getPlatformTransfersTotal(platform.getId(), initialDate, finalDate);

            final var report = new PortalPlatformDto(
                    platform.getId(),
                    platform.getName(),
                    balance.getId(),
                    balance.getInitialBalance(),
                    balance.getFinalBalance(),
                    transfersAmount,
                    transfersTotal
            );

            platformReports.add(report);
        });

        return platformReports;
    }

    @Transactional(rollbackFor = AppException.class)
    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public PlatformResponse update(int platformId, PlatformRequest request) {
        final var platform = repository.findById(platformId)
                .orElseThrow(() -> new NotFoundException("Plataforma no encontrada: " + platformId));

        if (platform.getEnabled().equals(Boolean.FALSE)) {
            throw new NotFoundException("Plataforma no encontrada: " + platformId);
        }

        platform.setName(request.name() == null ? platform.getName() : request.name());

        return mapper.toResponse(repository.save(platform));
    }

    @Secured(value = ADMIN_AUTHORITY)
    @Override
    public boolean delete(int platformId) {
        final var platformFound = repository.findById(platformId);

        if (platformFound.isEmpty()) {
            return false;
        }

        platformFound.get().setEnabled(false);

        repository.save(platformFound.get());

        return true;
    }

    private PlatformBalanceResponse getPlatformBalance(int platformId, LocalDateTime initialDate, LocalDateTime finalDate) {
        final var platformBalance = balanceRepository.findByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, initialDate, finalDate);

        if (platformBalance.isPresent()) {
            return balanceMapper.toResponse(platformBalance.get());
        }

        final var request = new PlatformBalanceRequest(platformId, 0, 0);

        return balanceMapper.toResponse(balanceRepository.save(balanceMapper.toEntity(request)));
    }

    private int getPlatformTransfersAmount(int platformId, LocalDateTime initialDate, LocalDateTime finalDate) {
        return transferRepository.countByPlatformIdAndEnabledTrueAndCreatedDateBetween(platformId, initialDate, finalDate);
    }

    private int getPlatformTransfersTotal(int platformId, LocalDateTime initialDate, LocalDateTime finalDate) {
        final var total = transferRepository.calculateTotalByPlatformIdAndCreatedDateBetween(platformId, initialDate, finalDate);
        return total != null ? total : 0;
    }
}
