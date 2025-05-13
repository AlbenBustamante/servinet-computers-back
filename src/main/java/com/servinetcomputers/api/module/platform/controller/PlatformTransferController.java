package com.servinetcomputers.api.module.platform.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.CreatePlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.DeletePlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.GetPlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.application.usecase.transfer.UpdatePlatformTransferUseCase;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.dto.CreatePlatformTransferWithVouchersDto;
import com.servinetcomputers.api.module.platform.domain.dto.PlatformTransferDto;
import com.servinetcomputers.api.module.platform.domain.dto.UpdatePlatformTransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * The transfer's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping(path = "/platform-transfers")
@RestController
public class PlatformTransferController {
    private final ObjectMapper mapper;
    private final CreatePlatformTransferUseCase createPlatformTransferUseCase;
    private final GetPlatformTransferUseCase getPlatformTransferUseCase;
    private final UpdatePlatformTransferUseCase updatePlatformTransferUseCase;
    private final DeletePlatformTransferUseCase deletePlatformTransferUseCase;

    @PostMapping
    public ResponseEntity<PlatformTransferDto> register(@RequestParam(name = "request") String request, @RequestParam(name = "vouchers", required = false) MultipartFile[] vouchers) throws JsonProcessingException {
        final var createPlatformTransferDto = mapper.readValue(request, CreatePlatformTransferDto.class);
        final var platformTransferRequest = CreatePlatformTransferWithVouchersDto.fromDto(createPlatformTransferDto);

        return ResponseEntity.status(CREATED).body(createPlatformTransferUseCase.call(platformTransferRequest, vouchers));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PlatformTransferDto> get(@PathVariable("id") int transferId) {
        return ResponseEntity.ok(getPlatformTransferUseCase.call(transferId));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<PlatformTransferDto> update(@PathVariable("id") int transferId, @RequestBody UpdatePlatformTransferDto updatePlatformTransferDto) {
        return ResponseEntity.ok(updatePlatformTransferUseCase.call(transferId, updatePlatformTransferDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int transferId) {
        deletePlatformTransferUseCase.call(transferId);
        return ResponseEntity.ok().build();
    }
}
