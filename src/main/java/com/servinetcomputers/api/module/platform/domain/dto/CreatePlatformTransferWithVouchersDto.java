package com.servinetcomputers.api.module.platform.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The transfer dto model for requests.
 */
@Getter
@Setter
public class CreatePlatformTransferWithVouchersDto {
    private Integer platformId, value;
    private String[] voucherUrls;
    private PlatformDto platform;

    public CreatePlatformTransferWithVouchersDto(Integer platformId, Integer value) {
        this.platformId = platformId;
        this.value = value;
    }

    public static CreatePlatformTransferWithVouchersDto fromDto(CreatePlatformTransferDto createPlatformTransferDto) {
        return new CreatePlatformTransferWithVouchersDto(createPlatformTransferDto.platformId(), createPlatformTransferDto.value());
    }
}
