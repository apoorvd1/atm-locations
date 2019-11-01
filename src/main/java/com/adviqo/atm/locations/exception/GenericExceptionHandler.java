package com.adviqo.atm.locations.exception;

import com.adviqo.atm.locations.error.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@ControllerAdvice
public class GenericExceptionHandler {

    private static final Logger EXCEPTION_LOGGER = LoggerFactory.getLogger(GenericExceptionHandler.class);

    private static final String GENERIC_ERROR_MESSAGE = "Something Went Wrong, Server Error";

    /**
     * Handle Exception for root error
     *
     * @param ex ex
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        UUID transactionId = UUID.randomUUID();
        EXCEPTION_LOGGER.error("handle Exception {}", transactionId);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                .body(new ApiError(transactionId, GENERIC_ERROR_MESSAGE));
    }

    /**
     * Exception Handler for internal static data
     *
     * @param ex ex
     * @return ResponseEntity
     */
    @ExceptionHandler(AtmLocationRuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleAtmLocationException(Exception ex) {
        UUID transactionId = UUID.randomUUID();
        EXCEPTION_LOGGER.error("handle Exception {}", transactionId);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON)
                .body(new ApiError(transactionId, ex.getMessage()));
    }
}
