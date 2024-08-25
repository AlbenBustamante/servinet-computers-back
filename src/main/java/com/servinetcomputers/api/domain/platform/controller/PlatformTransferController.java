package com.servinetcomputers.api.domain.platform.controller;

import com.servinetcomputers.api.domain.platform.abs.IPlatformTransferService;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.dto.PlatformTransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * The transfer's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/platform-transfers")
@RestController
public class PlatformTransferController {

    private final IPlatformTransferService transferService;

    @PostMapping
    public ResponseEntity<PlatformTransferResponse> register(@RequestBody PlatformTransferRequest request) {
        return ResponseEntity.status(CREATED).body(transferService.create(request));
    }

    @GetMapping("/{transferId}")
    public ResponseEntity<PlatformTransferResponse> get(@PathVariable("transferId") int transferId) {
        return ResponseEntity.ok(transferService.get(transferId));
    }

    @PatchMapping("/{transferId}")
    public ResponseEntity<PlatformTransferResponse> update(@PathVariable("transferId") int transferId, @RequestBody PlatformTransferRequest request) {
        return ResponseEntity.ok(transferService.update(transferId, request));
    }

    @DeleteMapping("/{transferId}")
    public ResponseEntity<Boolean> delete(@PathVariable("transferId") int transferId) {
        return ResponseEntity.ok(transferService.delete(transferId));
    }

}
