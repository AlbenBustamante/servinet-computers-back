package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * The {@link Transfer} repository.
 */
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    /**
     * Search all the transfers created between two dates.
     *
     * @param campusId    the campus to find.
     * @param isAvailable to check if the transfer is enabled.
     * @param firstDate   the first date.
     * @param lastDate    the second and last date.
     * @param pageable    the pageable.
     * @return a {@link Page} of all the transfers found.
     */
    Page<Transfer> findAllByCampusIdAndIsAvailableAndCreatedAtBetween(int campusId, boolean isAvailable, LocalDateTime firstDate, LocalDateTime lastDate, Pageable pageable);

}
