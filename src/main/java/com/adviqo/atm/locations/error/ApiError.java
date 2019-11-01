package com.adviqo.atm.locations.error;

import java.util.UUID;

public class ApiError {

    private String message;

    private UUID transactionId;

    public ApiError(UUID transactionId, String message) {
        this.transactionId = transactionId;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public UUID getTransactionId() {
        return transactionId;
    }
}
