package com.adviqo.atm.locations.exception;

public class AtmLocationRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3411727017553334277L;

    public AtmLocationRuntimeException() {
        super();
    }

    public AtmLocationRuntimeException(Throwable cause) {
        super(cause);
    }

    public AtmLocationRuntimeException(String message) {
        super(message);
    }

    public AtmLocationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
