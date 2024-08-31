package com.servinetcomputers.api.domain.platform.abs;

import com.servinetcomputers.api.domain.platform.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.platform.entity.PlatformTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The transfer's models mapper.
 */
@Mapper(componentModel = "spring")
public interface PlatformTransferMapper {

    PlatformTransferResponse toResponse(PlatformTransfer entity);

    List<PlatformTransferResponse> toResponses(List<PlatformTransfer> entities);

    @Mapping(target = "voucherUrls", ignore = true)
    @Mapping(target = "platform", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    PlatformTransfer toEntity(PlatformTransferRequest req);

}
