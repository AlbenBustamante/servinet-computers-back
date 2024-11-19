package com.servinetcomputers.api.domain.safes.dto;

import com.servinetcomputers.api.domain.base.BaseDto;

public record SafeDetailRequest(int safeId, BaseDto initialBase, BaseDto finalBase) {
}
