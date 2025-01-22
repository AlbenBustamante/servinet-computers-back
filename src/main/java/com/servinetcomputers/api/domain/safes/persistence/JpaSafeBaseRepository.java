package com.servinetcomputers.api.domain.safes.persistence;

import com.servinetcomputers.api.domain.safes.persistence.entity.SafeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSafeBaseRepository extends JpaRepository<SafeBase, Integer> {
}
