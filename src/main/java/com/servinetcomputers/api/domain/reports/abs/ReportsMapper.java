package com.servinetcomputers.api.domain.reports.abs;

import com.servinetcomputers.api.domain.expense.persistence.mapper.ExpenseMapper;
import com.servinetcomputers.api.domain.platform.abs.PlatformTransferMapper;
import com.servinetcomputers.api.domain.reports.dto.Reports;
import com.servinetcomputers.api.domain.reports.dto.ReportsResponse;
import com.servinetcomputers.api.domain.transaction.abs.TransactionDetailMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PlatformTransferMapper.class, ExpenseMapper.class, TransactionDetailMapper.class})
public interface ReportsMapper {

    ReportsResponse toResponse(Reports reports);

}
