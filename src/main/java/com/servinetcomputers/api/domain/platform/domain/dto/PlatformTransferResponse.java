package com.servinetcomputers.api.domain.platform.domain.dto;

import com.servinetcomputers.api.domain.ModelResponse;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * The transfer dto model for responses.
 */
@Getter
public class PlatformTransferResponse extends ModelResponse {
    private final int platformId, value;
    private final String platformName;
    private final String[] voucherUrls;

    public PlatformTransferResponse(int id, boolean enabled, LocalDateTime createdDate, LocalDateTime modifiedDate,
                                    String createdBy, String modifiedBy, int platformId, int value, String platformName, String[] voucherUrls) {
        super(id, enabled, createdDate, modifiedDate, createdBy, modifiedBy);
        this.platformId = platformId;
        this.value = value;
        this.platformName = platformName;
        this.voucherUrls = voucherUrls;
    }
}
