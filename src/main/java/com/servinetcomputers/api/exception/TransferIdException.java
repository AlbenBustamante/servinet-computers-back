package com.servinetcomputers.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class TransferIdException extends AppException {
    private final int transferId;

    protected TransferIdException(String detail, HttpStatus status, String title, int transferId) {
        super(detail, status, title);
        this.transferId = transferId;
    }
}
