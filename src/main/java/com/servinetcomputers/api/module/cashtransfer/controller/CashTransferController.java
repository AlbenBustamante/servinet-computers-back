package com.servinetcomputers.api.module.cashtransfer.controller;

import com.servinetcomputers.api.module.cashtransfer.application.usecase.CreateCashTransferUseCase;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.DeleteCashTransferUseCase;
import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetAvailableTransfersUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.AvailableTransfersDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CashTransferDto;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.CreateCashTransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cash-transfers")
public class CashTransferController {
    private final CreateCashTransferUseCase createCashTransferUseCase;
    private final GetAvailableTransfersUseCase getAvailableTransfersUseCase;
    private final DeleteCashTransferUseCase deleteCashTransferUseCase;

    @PostMapping
    public ResponseEntity<CashTransferDto> create(@RequestBody CreateCashTransferDto createCashTransferDto) {
        return ResponseEntity.ok(createCashTransferUseCase.call(createCashTransferDto));
    }

    @GetMapping
    public ResponseEntity<AvailableTransfersDto> getAvailableTransfers() {
        return ResponseEntity.ok(getAvailableTransfersUseCase.call());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int cashTransferId,
                                          @RequestParam("cashRegisterDetailId") int cashRegisterDetailId,
                                          @RequestParam("tempCode") int tempCode) {
        deleteCashTransferUseCase.call(cashRegisterDetailId, cashTransferId, tempCode);
        return ResponseEntity.noContent().build();
    }
}
