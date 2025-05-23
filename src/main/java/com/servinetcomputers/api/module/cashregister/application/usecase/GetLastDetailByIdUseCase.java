package com.servinetcomputers.api.module.cashregister.application.usecase;

import com.servinetcomputers.api.core.usecase.UseCase;
import com.servinetcomputers.api.module.cashregister.domain.dto.CashRegisterDetailDto;

/**
 * Obtiene el último movimiento de caja realizado según el ID de la caja registradora.
 * <p>Recibe el {@code ID} de la caja registradora a buscar.</p>
 * <p>Devuelve el {@link CashRegisterDetailDto} encontrado.</p>
 */
public interface GetLastDetailByIdUseCase extends UseCase<CashRegisterDetailDto, Integer> {
}
