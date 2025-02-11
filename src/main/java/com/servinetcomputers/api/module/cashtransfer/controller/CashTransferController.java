package com.servinetcomputers.api.module.cashtransfer.controller;

import com.servinetcomputers.api.module.cashtransfer.application.usecase.GetAvailableTransfersUseCase;
import com.servinetcomputers.api.module.cashtransfer.domain.dto.AvailableTransfersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cash-transfers")
public class CashTransferController {
    private final GetAvailableTransfersUseCase getAvailableTransfersUseCase;

    @GetMapping
    public ResponseEntity<AvailableTransfersDto> getAvailableTransfers() {
        return ResponseEntity.ok(getAvailableTransfersUseCase.call());
    }
}
