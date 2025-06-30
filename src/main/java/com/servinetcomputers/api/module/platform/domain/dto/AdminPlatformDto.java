package com.servinetcomputers.api.module.platform.domain.dto;

import java.util.List;

public record AdminPlatformDto(
        PlatformStatsDto platform,
        List<PlatformTransferDto> transfers
) {
}
