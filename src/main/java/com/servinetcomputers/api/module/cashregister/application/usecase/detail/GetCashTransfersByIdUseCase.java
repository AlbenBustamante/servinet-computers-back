package com.servinetcomputers.api.module.cashregister.application.usecase.detail;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.core.usecase.UseCaseBiParam;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import org.springframework.data.domain.Pageable;

/**
 * Gets cash transfers by a {@code Cash Register Detail}.
 * <p>Receives the {@code CashRegisterDetail ID}.</p>
 * <p>Receives a {@link Pageable} data.</p>
 * <p>Returns a {@link PageResponse} with the results.</p>
 */
public interface GetCashTransfersByIdUseCase extends UseCaseBiParam<PageResponse<CashTransferDto>, Integer, Pageable> {
}
