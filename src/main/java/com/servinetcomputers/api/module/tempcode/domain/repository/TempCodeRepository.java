package com.servinetcomputers.api.module.tempcode.domain.repository;

import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeRequest;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;

import java.util.Optional;

public interface TempCodeRepository {
    TempCodeResponse save(TempCodeRequest request);

    void save(TempCodeResponse response);

    Optional<TempCodeResponse> getLast();
}
