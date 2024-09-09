package com.servinetcomputers.api.domain.user.dto;

import com.servinetcomputers.api.domain.platform.entity.PlatformTransfer;

import java.util.List;

public record Reports(
        List<PlatformTransfer> platformTransfers
) {
}
