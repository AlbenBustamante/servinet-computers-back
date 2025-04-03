package com.servinetcomputers.api.module.tempcode.domain.repository;

import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeRequest;
import com.servinetcomputers.api.module.tempcode.domain.dto.TempCodeResponse;

public interface TempCodeRepository {
    TempCodeResponse save(TempCodeRequest request);

    boolean existsByCode(int code);
}
