package com.servinetcomputers.api.module.cashtransfer.application.usecase;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import org.springframework.data.domain.Pageable;

/**
 * Creates a new {@code CashTransfer} and gets an updated list.
 * <p>Receives the {@link CreateCashTransferDto} data.</p>
 * <p>Receives the {@link Pageable} data.</p>
 * <p>Returns a {@link PageResponse} with the results.</p>
 */
public interface CreateCashTransferUseCase extends UseCaseBiParam<PageResponse<CashTransferDto>, CreateCashTransferDto, Pageable> {
}
