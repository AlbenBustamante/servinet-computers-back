package com.servinetcomputers.api.module.platform.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CreatePlatformTransferDto(
        int platformId,
        int value,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate date
) {
}
