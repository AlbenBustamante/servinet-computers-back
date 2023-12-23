package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.response.DashboardResponse;
import com.servinetcomputers.api.dto.response.DashboardResponse.CampusDashboardResponse;
import com.servinetcomputers.api.dto.response.DashboardResponse.PlatformDetailDashboardResponse;
import com.servinetcomputers.api.exception.UserNotFoundException;
import com.servinetcomputers.api.exception.UserUnavailableException;
import com.servinetcomputers.api.model.User;
import com.servinetcomputers.api.repository.TransferRepository;
import com.servinetcomputers.api.repository.UserRepository;
import com.servinetcomputers.api.service.IDashboardService;
import com.servinetcomputers.api.util.ICurrencyFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.servinetcomputers.api.util.constants.LocalConstants.DEFAULT_ZONE;

/**
 * The Admin Dashboard Reports service implementation.
 */
@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements IDashboardService {

    private final UserRepository userRepository;
    private final TransferRepository transferRepository;
    private final ICurrencyFormatter currencyFormatter;

    @Override
    public DashboardResponse getReports(int userId) {
        final var user = getUser(userId);

        final var today = LocalDate.now(DEFAULT_ZONE);
        final var startDate = LocalDateTime.of(today, LocalTime.MIN);
        final var endDate = LocalDateTime.of(today, LocalTime.now(DEFAULT_ZONE));

        final List<CampusDashboardResponse> campuses = new ArrayList<>(user.getCampuses().size());
        final List<PlatformDetailDashboardResponse> temporaryPlatforms = new ArrayList<>();
        var total = BigDecimal.ZERO;

        for (final var campus : user.getCampuses()) {
            /* -- CAMPUSES -- */
            var campusTotal = BigDecimal.ZERO;

            final var transfers = transferRepository
                    .findAllByCampusIdAndIsAvailableAndCreatedAtBetween(campus.getId(), true, startDate, endDate);

            for (final var transfer : transfers) {
                campusTotal = campusTotal.add(transfer.getValue());
            }

            campuses.add(new CampusDashboardResponse(campus.getNumeral(), currencyFormatter.format(campusTotal)));

            total = total.add(campusTotal);

            /* -- PLATFORMS -- */
            for (final var platform : campus.getPlatforms()) {
                var platformTotal = BigDecimal.ZERO;
                var transfersAmount = 0;

                for (final var transfer : platform.getTransfers()) {
                    if (transfer.getCreatedAt().toLocalDate().equals(today) && transfer.getCampusId().equals(campus.getId())) {
                        platformTotal = platformTotal.add(transfer.getValue());
                        transfersAmount++;
                    }
                }

                temporaryPlatforms.add(new PlatformDetailDashboardResponse(
                        platform.getId(), platform.getName(), transfersAmount, String.valueOf(platformTotal))
                );
            }
        }

        final List<PlatformDetailDashboardResponse> platforms = new ArrayList<>();

        temporaryPlatforms.forEach(platform -> {
            var platformValue = BigDecimal.ZERO;
            var transfersAmount = 0;

            for (final var childPlatform : temporaryPlatforms) {
                if (platform.platformId() == childPlatform.platformId()) {
                    platformValue = platformValue.add(new BigDecimal(childPlatform.total()));
                    transfersAmount += childPlatform.transfersAmount();
                }
            }

            final var alreadyExists = platforms.stream().anyMatch(p -> p.platformId() == platform.platformId());

            if (!alreadyExists) {
                platforms.add(new PlatformDetailDashboardResponse(
                        platform.platformId(), platform.platformName(), transfersAmount, currencyFormatter.format(platformValue)
                ));
            }
        });

        return new DashboardResponse(currencyFormatter.format(total), campuses, temporaryPlatforms, platforms);
    }

    private User getUser(int userId) {
        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (!user.getIsAvailable()) {
            throw new UserUnavailableException(userId);
        }

        return user;
    }

}
