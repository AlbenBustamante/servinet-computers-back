package com.servinetcomputers.api.module.cashregister.domain;

import com.servinetcomputers.api.core.audit.infra.Auditable;
import com.servinetcomputers.api.core.util.enums.CashRegisterDetailStatus;
import com.servinetcomputers.api.module.user.domain.User;

import java.time.LocalTime;

/**
 * Modelo de dominio para movimientos de cajas registradoras.
 *
 * @param id              {@code ID}.
 * @param workingHours    Horas: Entrada - Almuerzo Entrada - Almuerzo Salida - Salida.
 * @param initialBase     Base inicial.
 * @param finalBase       Base final.
 * @param baseObservation Observación de la base inicial.
 * @param status          Estado actual.
 * @param cashRegister    Caja registradora.
 * @param user            Usuario/empleado.
 * @param audit           {@link Auditable}
 * @see CashRegister
 * @see User
 */
public record CashRegisterDetail(
        Integer id,
        LocalTime[] workingHours,
        String initialBase,
        String finalBase,
        String baseObservation,
        CashRegisterDetailStatus status,
        CashRegister cashRegister,
        User user,
        Auditable audit
) {
}
