package com.servinetcomputers.api.module.changelog.domain.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.servinetcomputers.api.module.changelog.domain.dto.ChangeLogDto;
import com.servinetcomputers.api.module.changelog.domain.dto.CreateChangeLogDto;

import java.util.List;

public interface ChangeLogRepository {
    ChangeLogDto save(CreateChangeLogDto dto) throws JsonProcessingException;

    List<ChangeLogDto> getAllByCashRegisterDetailId(int cashRegisterDetailId);
}
