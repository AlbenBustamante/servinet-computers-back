package com.servinetcomputers.api.module.transaction.controller;

import com.servinetcomputers.api.core.page.PageResponse;
import com.servinetcomputers.api.module.transaction.application.usecase.CreateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.application.usecase.DeleteTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.application.usecase.UpdateTransactionDetailUseCase;
import com.servinetcomputers.api.module.transaction.domain.dto.CreateTransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.TransactionDetailDto;
import com.servinetcomputers.api.module.transaction.domain.dto.UpdateTransactionDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/transaction-details")
@RestController
public class TransactionDetailController {
    private final CreateTransactionDetailUseCase createTransactionDetailUseCase;
    private final UpdateTransactionDetailUseCase updateTransactionDetailUseCase;
    private final DeleteTransactionDetailUseCase deleteTransactionDetailUseCase;

    @PostMapping
    public ResponseEntity<PageResponse<TransactionDetailDto>> create(
            @RequestBody CreateTransactionDetailDto request,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "direction", defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(value = "property", defaultValue = "createdDate") String property
    ) {
        final var pageable = PageRequest.of(pageNumber, pageSize, direction, property);
        return ResponseEntity.ok(createTransactionDetailUseCase.call(request, pageable));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<TransactionDetailDto> update(@PathVariable("id") Integer transactionDetailId, @RequestBody UpdateTransactionDetailDto updateTransactionDetailDto) {
        return ResponseEntity.ok(updateTransactionDetailUseCase.call(transactionDetailId, updateTransactionDetailDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer transactionDetailId, @RequestParam("tempCode") Integer tempCode) {
        deleteTransactionDetailUseCase.call(transactionDetailId, tempCode);
        return ResponseEntity.noContent().build();
    }
}
