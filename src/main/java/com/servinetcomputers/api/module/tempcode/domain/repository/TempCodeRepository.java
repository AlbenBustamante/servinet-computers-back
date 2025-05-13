package com.servinetcomputers.api.module.tempcode.domain.repository;

import com.servinetcomputers.api.module.tempcode.domain.dto.CreateTempCodeDto;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeDto;

import java.util.Optional;

public interface TempCodeRepository {
    TempCodeDto save(CreateTempCodeDto request);

    void save(TempCodeDto response);

    Optional<TempCodeDto> getLast();
}
