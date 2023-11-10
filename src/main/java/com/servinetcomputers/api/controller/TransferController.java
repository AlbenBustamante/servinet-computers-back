package com.servinetcomputers.api.controller;

import com.servinetcomputers.api.dto.request.PageRequest;
import com.servinetcomputers.api.dto.request.TransferRequest;
import com.servinetcomputers.api.dto.response.PageResponse;
import com.servinetcomputers.api.dto.response.TransferResponse;
import com.servinetcomputers.api.service.ITransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @GetMapping
    public ResponseEntity<PageResponse<TransferResponse>> getAllByCreationDate(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "createdAt") String property,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate
    ) {
        final var pageRequest = new PageRequest(size, page, direction, property);

        if (startDate == null) {
            startDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        }

        if (endDate == null) {
            endDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        }

        return ResponseEntity.ok(transferService.getAllByCreationDateBetween(startDate, endDate, pageRequest));
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
