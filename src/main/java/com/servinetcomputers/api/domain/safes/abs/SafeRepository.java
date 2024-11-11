package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.Safe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SafeRepository extends JpaRepository<Safe, Integer> {

    boolean existsByNumeralAndEnabledTrue(int numeral);

    List<Safe> findAllByEnabledTrue();

    Optional<Safe> findByIdAndEnabledTrue(int id);
}
