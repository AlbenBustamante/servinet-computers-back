package com.servinetcomputers.api.domain.user.abs;

import com.servinetcomputers.api.domain.expense.abs.ExpenseMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferMapper;
import com.servinetcomputers.api.domain.user.dto.Reports;
import com.servinetcomputers.api.domain.user.dto.ReportsResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PlatformTransferMapper.class, ExpenseMapper.class})
public interface ReportsMapper {

    ReportsResponse toResponse(Reports reports);

}
