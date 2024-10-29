package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.dto.SafeRequest;
import com.servinetcomputers.api.domain.safes.dto.SafeResponse;

import java.util.List;

public interface ISafeService {

    SafeResponse create(SafeRequest request);

    List<SafeResponse> getAll();

}
