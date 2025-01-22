package com.servinetcomputers.api.domain.safes.persistence;

import com.servinetcomputers.api.domain.safes.persistence.entity.Safe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSafeRepository extends JpaRepository<Safe, Integer> {
    boolean existsByNumeralAndEnabledTrue(int numeral);

    List<Safe> findAllByEnabledTrue();
}
