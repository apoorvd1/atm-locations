package com.adviqo.atm.locations.service;

import com.adviqo.atm.locations.exception.AtmLocationRuntimeException;
import com.adviqo.atm.locations.helper.MockData;
import com.adviqo.atm.locations.model.Locations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RunWith(SpringRunner.class) //SpringRunner is an alias for the SpringJUnit4ClassRunner
@SpringBootTest
public class LocationServiceSeverImplTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LocationServiceImpl locationService;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testPositiveLocationServiceImpl() {
        mockServer.expect(requestTo("https://www.ing.nl/api/locator/atms/"))
                .andRespond(withSuccess(MockData.getLocators(), MediaType.APPLICATION_JSON));

        List<Locations> result = locationService.findLocation();
        System.out.println("testGetRootResource: " + result);

        mockServer.verify();
        assertEquals("50", result.get(0).getDistance());
        assertEquals("ING", result.get(0).getType());
        assertEquals("Deventer", result.get(0).getAddress().getCity());
        assertEquals("1", result.get(0).getAddress().getHouseNumber());
        assertEquals("7412 SB", result.get(0).getAddress().getPostalCode());
        assertEquals("Johannes van Vlotenlaan", result.get(0).getAddress().getStreet());
        assertEquals("52.26656", result.get(0).getAddress().getGeoLocation().getLat());
        assertEquals("6.14299", result.get(0).getAddress().getGeoLocation().getLng());
    }

    @Test(expected = AtmLocationRuntimeException.class)
    public void testNegativeLocationServiceImpl() {
        mockServer.expect(requestTo("https://www.ing.nl/api/locator/atms/"))
                .andRespond(withServerError());

        List<Locations> result = locationService.findLocation();
        System.out.println("testGetRootResource: " + result);

        mockServer.verify();
        assertEquals("50", result.get(0).getDistance());
        assertEquals("ING", result.get(0).getType());
        assertEquals("Deventer", result.get(0).getAddress().getCity());
        assertEquals("1", result.get(0).getAddress().getHouseNumber());
        assertEquals("7412 SB", result.get(0).getAddress().getPostalCode());
        assertEquals("Johannes van Vlotenlaan", result.get(0).getAddress().getStreet());
        assertEquals("52.26656", result.get(0).getAddress().getGeoLocation().getLat());
        assertEquals("6.14299", result.get(0).getAddress().getGeoLocation().getLng());
    }

}
