package com.adviqo.atm.locations.service;

import com.adviqo.atm.locations.helper.MockData;
import com.adviqo.atm.locations.model.Locations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationServiceSeverImplTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LocationServiceImpl locationService;

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

        mockServer.verify();
        assertEquals("50", result.get(0).getDistance());
        assertEquals("ING", result.get(0).getType());
        assertEquals("Deventer", result.get(0).getAddress().getCity());
        assertEquals("1", result.get(0).getAddress().getHousenumber());
        assertEquals("7412 SB", result.get(0).getAddress().getPostalcode());
        assertEquals("Johannes van Vlotenlaan", result.get(0).getAddress().getStreet());
        assertEquals("52.26656", result.get(0).getAddress().getGeoLocation().getLat());
        assertEquals("6.14299", result.get(0).getAddress().getGeoLocation().getLng());
    }

    @Test(expected = HttpServerErrorException.class)
    public void testNegativeLocationServiceImplWithServerError() {
        mockServer.expect(requestTo("https://www.ing.nl/api/locator/atms/"))
                .andRespond(withServerError());

        List<Locations> result = locationService.findLocation();

        mockServer.verify();
        assertNotNull(result);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testNegativeLocationServiceImplWithNotFoundStatus() {
        mockServer.expect(requestTo("https://www.ing.nl/api/locator/atms/"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        List<Locations> result = locationService.findLocation();

        mockServer.verify();
        assertNotNull(result);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testNegativeLocationServiceImplWithBadRequest() {
        mockServer.expect(requestTo("https://www.ing.nl/api/locator/atms/"))
                .andRespond(withStatus(HttpStatus.BAD_REQUEST));

        List<Locations> result = locationService.findLocation();

        mockServer.verify();
        assertNotNull(result);
    }

    @Test(expected = HttpClientErrorException.class)
    public void testNegativeLocationServiceImplWithForbidden() {
        mockServer.expect(requestTo("https://www.ing.nl/api/locator/atms/"))
                .andRespond(withStatus(HttpStatus.FORBIDDEN));

        List<Locations> result = locationService.findLocation();

        mockServer.verify();
        assertNotNull(result);
    }

}
