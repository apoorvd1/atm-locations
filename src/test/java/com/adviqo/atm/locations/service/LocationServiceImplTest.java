package com.adviqo.atm.locations.service;

import com.adviqo.atm.locations.health.model.LocatorIndicators;
import com.adviqo.atm.locations.helper.MockData;
import com.adviqo.atm.locations.model.Locations;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;


@Ignore
@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LocationServiceImpl locationService;

    @Test
    public void shouldFindAtmLocations() {
        Locations location = MockData.getLocation();
        Mockito.when(restTemplate.getForEntity("http://localhost:8080/api/v1/locations", String.class))
          .thenReturn(new ResponseEntity<String>(HttpStatus.OK));
        List<Locations> locations = locationService.findLocation();
        assertNotNull(locations);
    }

}