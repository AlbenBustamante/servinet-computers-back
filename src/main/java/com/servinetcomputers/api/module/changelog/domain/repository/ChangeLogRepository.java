package com.servinetcomputers.api.module.changelog.domain.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogResponse;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;

import java.util.List;

public interface ChangeLogRepository {
    ChangeLogResponse save(CreateChangeLogDto dto) throws JsonProcessingException;

    List<ChangeLogResponse> getAllByCashRegisterDetailId(int cashRegisterDetailId);
}
