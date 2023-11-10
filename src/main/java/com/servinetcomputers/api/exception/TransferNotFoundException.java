package com.servinetcomputers.api.exception;

import org.springframework.http.HttpStatus;

public class TransferNotFoundException extends TransferIdException {
    public TransferNotFoundException(int transferId) {
        super("Transfer ID #" + transferId + " is not found",
                HttpStatus.NOT_FOUND,
                "TRANSFER NOT FOUND",
                transferId);
    }
}
