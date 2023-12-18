package com.servinetcomputers.api.service.impl;

import com.servinetcomputers.api.dto.response.DashboardResponse;
import com.servinetcomputers.api.dto.response.DashboardResponse.CampusDashboardResponse;
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

import static com.servinetcomputers.api.util.constants.LocalUtil.DEFAULT_ZONE;

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

        final var startDate = LocalDateTime.of(LocalDate.now(DEFAULT_ZONE), LocalTime.MIN);
        final var endDate = LocalDateTime.of(LocalDate.now(DEFAULT_ZONE), LocalTime.now(DEFAULT_ZONE));

        final List<CampusDashboardResponse> campuses = new ArrayList<>(user.getCampuses().size());
        var total = BigDecimal.ZERO;

        for (final var campus : user.getCampuses()) {
            var campusTotal = BigDecimal.ZERO;

            final var transfers = transferRepository
                    .findAllByCampusIdAndIsAvailableAndCreatedAtBetween(campus.getId(), true, startDate, endDate);

            for (final var transfer : transfers) {
                campusTotal = campusTotal.add(transfer.getValue());
            }

            campuses.add(new CampusDashboardResponse(campus.getNumeral(), currencyFormatter.format(campusTotal)));

            total = total.add(campusTotal);
        }

        return new DashboardResponse(currencyFormatter.format(total), campuses);
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
