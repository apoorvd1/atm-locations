package com.adviqo.atm.locations.error;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ApiError {

    private String message;

    private UUID transactionId;

    public ApiError(UUID transactionId, String message) {
        this.transactionId = transactionId;
        this.message = message;
    }
}
