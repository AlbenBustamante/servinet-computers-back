package com.servinetcomputers.api.module.platform.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The transfer dto model for requests.
 */
@RequiredArgsConstructor
@Getter
@Setter
public class PlatformTransferRequest {
    private final Integer platformId;
    private final Integer value;
    private String[] voucherUrls;
    private PlatformResponse platform;
}
