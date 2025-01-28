package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.module.base.BaseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class SafeDetailRequest {
    private final int safeId;
    private final BaseDto initialBase, finalBase;
    private SafeResponse safe;
}
