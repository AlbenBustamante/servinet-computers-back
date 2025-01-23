package com.servinetcomputers.api.domain.cashregister.application.service.detail;

import com.servinetcomputers.api.core.datetime.DateTimeService;
import com.servinetcomputers.api.core.exception.AppException;
import com.servinetcomputers.api.core.exception.BadRequestException;
import com.servinetcomputers.api.core.exception.NotFoundException;
import com.servinetcomputers.api.domain.cashregister.application.usecase.detail.CreateCashRegisterDetailUseCase;
import com.servinetcomputers.api.domain.cashregister.domain.dto.CashRegisterDetailRequest;
import com.servinetcomputers.api.domain.cashregister.domain.dto.MyCashRegistersReports;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterDetailRepository;
import com.servinetcomputers.api.domain.cashregister.domain.repository.CashRegisterRepository;
import com.servinetcomputers.api.domain.cashregister.util.CashRegisterStatus;
import com.servinetcomputers.api.domain.user.application.usecase.GetCashRegisterReportsUseCase;
import com.servinetcomputers.api.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCashRegisterDetailService implements CreateCashRegisterDetailUseCase {
    private final CashRegisterDetailRepository repository;
    private final CashRegisterRepository cashRegisterRepository;
    private final UserRepository userRepository;
    private final DateTimeService dateTimeService;
    private final GetCashRegisterReportsUseCase getReportsUseCase;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public MyCashRegistersReports call(CashRegisterDetailRequest param) {
        final var today = dateTimeService.dateNow();
        final var startDate = dateTimeService.getMinByDate(today);
        final var endDate = dateTimeService.now();

        if (repository.existsByUserIdAndStatusNot(param.getUserId(), startDate, endDate, CashRegisterStatus.AVAILABLE)) {
            throw new BadRequestException("Ya tienes una caja en funcionamiento");
        }

        var cashRegister = cashRegisterRepository.get(param.getCashRegisterId())
                .orElseThrow(() -> new NotFoundException("Caja no encontrada: #" + param.getCashRegisterId()));

        if (cashRegister.getStatus().equals(CashRegisterStatus.OCCUPIED)) {
            throw new BadRequestException("La caja registradora ya está ocupada");
        }

        if (cashRegister.getStatus().equals(CashRegisterStatus.DISABLED)) {
            throw new BadRequestException("La caja registradora no está habilitada");
        }

        cashRegister.setStatus(CashRegisterStatus.OCCUPIED);
        cashRegister = cashRegisterRepository.save(cashRegister);

        param.setCashRegister(cashRegister);

        final var user = userRepository.get(param.getUserId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado: #" + param.getUserId()));

        param.setUser(user);
        repository.save(param);

        return getReportsUseCase.call(param.getUserId());
    }
}
