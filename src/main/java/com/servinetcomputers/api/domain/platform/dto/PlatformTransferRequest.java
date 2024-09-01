package com.servinetcomputers.api.domain.platform.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * The transfer dto model for requests.
 */
@Getter
@Setter
public class PlatformTransferRequest {
    private Integer platformId;
    private Integer value;
}
