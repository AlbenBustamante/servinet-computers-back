package com.servinetcomputers.api.module.platform.domain.dto;

import java.time.LocalDateTime;

public record CreatePlatformTransferDto(int platformId, int value, LocalDateTime date) {
}
