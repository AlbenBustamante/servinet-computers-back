package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class TransferUnavailableException extends TransferIdException {
    public TransferUnavailableException(int transferId) {
        super("The transfer ID #" + transferId + " is unavailable",
                HttpStatus.BAD_REQUEST,
                "TRANSFER UNAVAILABLE",
                transferId);
    }
}
