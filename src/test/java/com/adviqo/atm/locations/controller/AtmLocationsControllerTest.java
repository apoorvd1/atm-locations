package com.adviqo.atm.locations.controller;

import com.adviqo.atm.locations.AtmLocationApplication;
import com.adviqo.atm.locations.helper.MockData;
import com.adviqo.atm.locations.model.Locations;
import com.adviqo.atm.locations.service.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AtmLocationApplication.class, secure = false)
public class AtmLocationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AtmLocationsController atmLocationsController;

    @Mock
    private LocationService locationService;

    @Before
    public void setUp() {
        atmLocationsController = new AtmLocationsController(locationService);
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(atmLocationsController).build();
    }

    @Test
    public void testAtmLocationTestShouldNotNull() {
        assertNotNull(atmLocationsController);
    }

    @Test
    public void testAtmLocationTestShouldFindLocation() throws Exception {
        List<Locations> locations = MockData.getLocations();
        Mockito.when(locationService.findLocation()).thenReturn(locations);
        mockMvc.perform(get("/api/v1/locations/")).andExpect(status().isOk());
    }

    @Test
    public void testAtmLocationTestShouldThrowErrorForInvalidLocation() throws Exception {
        List<Locations> locations = MockData.getLocations();
        Mockito.when(locationService.findLocation()).thenReturn(locations);
        mockMvc.perform(get("/api/v2/locations/")).andExpect(status().is4xxClientError());
    }

}
