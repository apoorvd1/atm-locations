package com.adviqo.atm.locations.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AtmLocationRuntimeExceptionTest {

    @Test
    public void testAtmLocatorsRuntimeException() {
        RuntimeException actual = new AtmLocationRuntimeException();
        assertNull("Cause is not null", actual.getCause());
        assertNull("Message is not null", actual.getMessage());
    }

    @Test
    public void testAtmLocatorRuntimeExceptionString() {
        String message = "Test Error Message";

        RuntimeException actual = new AtmLocationRuntimeException(message);
        assertEquals("Message does not match", message, actual.getMessage());
    }

    @Test
    public void testAtmLocatorRunTimeExceptionThrowable() {
        Throwable cause = new Exception("Causing exception");
        RuntimeException actual = new AtmLocationRuntimeException(cause);
        assertEquals("Cause does not match constructor argument", cause, actual.getCause());
        assertEquals("Message is not String-ified cause", cause.toString(), actual.getMessage());
    }

    @Test
    public void testAtmLocatorRuntimeExceptionStringThrowable() {
        Throwable cause = new Exception("Causing Exception");
        String message = "Test Error Message";

        RuntimeException actual = new AtmLocationRuntimeException(message, cause);
        assertEquals("Cause does not match constructor argument", cause, actual.getCause());
        assertEquals("Message does not match constructor argument", message, actual.getMessage());
    }

}