package com.servinetcomputers.api.module.changelog.domain.repository;

import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;

public interface ChangeLogRepository {
    ChangeLogResponse save(CreateChangeLogDto dto);
}
