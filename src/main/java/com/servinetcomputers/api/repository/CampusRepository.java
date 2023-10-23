package com.servinetcomputers.api.repository;

import com.servinetcomputers.api.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@link Campus} repository.
 */
public interface CampusRepository extends JpaRepository<Campus, Integer> {
}
