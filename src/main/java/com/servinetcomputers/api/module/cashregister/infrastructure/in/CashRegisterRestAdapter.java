package com.servinetcomputers.api.module.cashregister.infrastructure.in;

import com.servinetcomputers.api.core.common.RestAdapter;
import com.servinetcomputers.api.module.base.Base;
import com.servinetcomputers.api.module.cashregister.application.port.in.CreateCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.DeleteCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.GetAllCashRegistersUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.GetCashRegisterMovementsByIdUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.GetLastBaseUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.UpdateCashRegisterUseCase;
import com.servinetcomputers.api.module.cashregister.application.port.in.command.CreateCashRegisterCommand;
import com.servinetcomputers.api.module.cashregister.application.port.in.command.UpdateCashRegisterCommand;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDetailDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.dto.CashRegisterDto;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.mapper.CashRegisterDetailDtoMapper;
import com.servinetcomputers.api.module.cashregister.infrastructure.in.rest.mapper.CashRegisterDtoMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestAdapter
@RequiredArgsConstructor
public class CashRegisterRestAdapter {
    private final CreateCashRegisterUseCase createCashRegisterUseCase;
    private final GetAllCashRegistersUseCase getAllCashRegistersUseCase;
    private final DeleteCashRegisterUseCase deleteCashRegisterUseCase;
    private final GetCashRegisterMovementsByIdUseCase getCashRegisterMovementsByIdUseCase;
    private final GetLastBaseUseCase getLastBaseUseCase;
    private final UpdateCashRegisterUseCase updateCashRegisterUseCase;
    private final CashRegisterDtoMapper mapper;
    private final CashRegisterDetailDtoMapper detailMapper;

    public CashRegisterDto create(CreateCashRegisterCommand command) {
        return mapper.toDto(createCashRegisterUseCase.create(command));
    }

    public List<CashRegisterDto> getAll() {
        return mapper.toDto(getAllCashRegistersUseCase.getAll());
    }

    public List<CashRegisterDetailDto> getMovements(Integer id) {
        return detailMapper.toDto(getCashRegisterMovementsByIdUseCase.getMovements(id));
    }

    public Base getLastBase(Integer id) {
        return getLastBaseUseCase.getLastBase(id);
    }

    public CashRegisterDto update(Integer id, UpdateCashRegisterCommand command) {
        return mapper.toDto(updateCashRegisterUseCase.update(id, command));
    }

    public void delete(Integer id) {
        deleteCashRegisterUseCase.delete(id);
    }
}
