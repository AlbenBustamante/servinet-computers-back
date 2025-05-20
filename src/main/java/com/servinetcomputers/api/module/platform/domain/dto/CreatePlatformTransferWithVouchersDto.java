package com.servinetcomputers.api.module.platform.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The transfer dto model for requests.
 */
@Getter
@Setter
public class CreatePlatformTransferWithVouchersDto {
    private Integer platformId, value;
    private String[] voucherUrls;
    private PlatformDto platform;
    private LocalDate date;

    public CreatePlatformTransferWithVouchersDto(Integer platformId, Integer value, LocalDate date) {
        this.platformId = platformId;
        this.value = value;
        this.date = date;
    }

    public static CreatePlatformTransferWithVouchersDto fromDto(CreatePlatformTransferDto createPlatformTransferDto) {
        return new CreatePlatformTransferWithVouchersDto(createPlatformTransferDto.platformId(), createPlatformTransferDto.value(), createPlatformTransferDto.date());
    }
}
