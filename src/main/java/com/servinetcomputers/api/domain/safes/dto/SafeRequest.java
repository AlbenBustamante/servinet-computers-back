package com.servinetcomputers.api.domain.safes.dto;

import com.servinetcomputers.api.domain.base.BaseDto;

public record SafeRequest(int numeral, BaseDto initialBase, BaseDto finalBase) {
}
