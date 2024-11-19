package com.servinetcomputers.api.domain.safes.abs;

import com.servinetcomputers.api.domain.safes.dto.SafeDetailResponse;

import java.util.List;

public interface ISafeDetailService {

    List<SafeDetailResponse> loadSafes();

}
