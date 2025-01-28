package com.servinetcomputers.api.module.safes.domain.dto;

import com.servinetcomputers.api.module.base.BaseDto;

public record SafeDetailRequest(int safeId, BaseDto initialBase, BaseDto finalBase) {
}
