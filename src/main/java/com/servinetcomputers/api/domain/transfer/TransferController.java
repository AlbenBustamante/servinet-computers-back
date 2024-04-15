package com.servinetcomputers.api.domain.transfer;

import com.servinetcomputers.api.domain.PageResponse;
import com.servinetcomputers.api.domain.transfer.abs.ITransferService;
import com.servinetcomputers.api.domain.transfer.dto.TransferRequest;
import com.servinetcomputers.api.domain.transfer.dto.TransferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * The transfer's routes/endpoints.
 */
@RequiredArgsConstructor
@RequestMapping("/transfers")
@RestController
public class TransferController {

    private final ITransferService transferService;

    @PostMapping
    public ResponseEntity<PageResponse<TransferResponse>> register(@RequestBody TransferRequest request) {
        return ResponseEntity.status(CREATED).body(transferService.create(request));
    }

    @GetMapping("/{transferId}")
    public ResponseEntity<PageResponse<TransferResponse>> get(@PathVariable("transferId") int transferId) {
        return ResponseEntity.ok(transferService.get(transferId));
    }

    @PatchMapping("/{transferId}")
    public ResponseEntity<PageResponse<TransferResponse>> update(@PathVariable("transferId") int transferId, @RequestBody TransferRequest request) {
        return ResponseEntity.ok(transferService.update(transferId, request));
    }

    @DeleteMapping("/{transferId}")
    public ResponseEntity<Boolean> delete(@PathVariable("transferId") int transferId) {
        return ResponseEntity.ok(transferService.delete(transferId));
    }

}
