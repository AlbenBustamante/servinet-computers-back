package com.servinetcomputers.api.module.safes.domain.dto;

import java.util.List;

public record SafeMovementDto(
        SafeDetailResponse safeDetail,
        List<SafeBaseResponse> bases
) {
}
