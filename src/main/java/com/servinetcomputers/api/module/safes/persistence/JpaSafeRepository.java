package com.servinetcomputers.api.module.safes.persistence;

import com.servinetcomputers.api.module.safes.persistence.entity.Safe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaSafeRepository extends JpaRepository<Safe, Integer> {
    boolean existsByNumeralAndEnabledTrue(int numeral);

    Optional<Safe> findByIdAndEnabledTrue(Integer id);

    List<Safe> findAllByEnabledTrue();
}
