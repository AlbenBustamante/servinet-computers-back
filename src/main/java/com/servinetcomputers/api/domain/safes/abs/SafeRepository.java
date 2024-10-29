package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.entity.Safe;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SafeRepository extends JpaRepository<Safe, Integer> {

    boolean existsByNumeralAndEnabledTrue(int numeral);

    List<Safe> findAllByEnabledTrue();
}
