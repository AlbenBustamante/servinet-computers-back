package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.entity.SafeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SafeBaseRepository extends JpaRepository<SafeBase, Integer> {
}
