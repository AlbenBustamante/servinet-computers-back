package com.servinetcomputers.api.domain.platform.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferRequest;
import com.servinetcomputers.api.domain.platform.domain.dto.PlatformTransferResponse;
import com.servinetcomputers.api.domain.platform.domain.repository.PlatformTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * The transfer's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/platform-transfers")
@RestController
public class PlatformTransferController {

    private final PlatformTransferRepository transferService;

    @PostMapping
    public ResponseEntity<PlatformTransferResponse> register(@RequestParam(name = "request") String request, @RequestParam(name = "vouchers", required = false) MultipartFile[] vouchers) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        final var platformTransferRequest = mapper.readValue(request, PlatformTransferRequest.class);

        return ResponseEntity.status(CREATED).body(transferService.create(platformTransferRequest, vouchers));
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
