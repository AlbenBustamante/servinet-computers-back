package com.servinetcomputers.api.module.platform.domain.dto;

import java.util.List;

public record AdminPlatformDto(
        PlatformDto platform,
        List<PlatformBalanceDto> balances,
        List<PlatformTransferDto> transfers
) {
}
