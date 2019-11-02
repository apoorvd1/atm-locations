package com.adviqo.atm.locations.exception;

import com.adviqo.atm.locations.error.ApiError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GenericExceptionHandlerTest {

    @InjectMocks
    private GenericExceptionHandler errorHandler;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(errorHandler).build();
    }

    @Test
    public void handleExceptionTest() {
        Exception ex = new Exception();
        ResponseEntity<ApiError> responseEntity = errorHandler.handleException(ex);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    public void handleAtmLocatorException() {
        Exception ex = new Exception();
        ResponseEntity<ApiError> responseEntity = errorHandler.handleAtmLocationException(ex);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}