package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class SafeBaseRequest {
    private final int safeDetailId;
    private final BaseDto baseDto;
    private SafeDetailResponse safeDetail;
}
