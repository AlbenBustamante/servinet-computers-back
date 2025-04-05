package com.servinetcomputers.api.module.changelog.persistence;

import com.servinetcomputers.api.module.changelog.persistence.entity.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChangeLogRepository extends JpaRepository<ChangeLog, Integer> {
}
