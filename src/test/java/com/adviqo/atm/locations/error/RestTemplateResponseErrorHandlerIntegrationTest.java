package com.adviqo.atm.locations.error;

import com.adviqo.atm.locations.exception.AtmLocationRuntimeException;
import com.adviqo.atm.locations.model.Locations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AtmLocationRuntimeException.class, Locations.class})
@RestClientTest
public class RestTemplateResponseErrorHandlerIntegrationTest {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test(expected = AtmLocationRuntimeException.class)
    public void givenRemoteApiCall_When404Error_ThrowNotFound() {
        assertNotNull(this.restTemplateBuilder);
        assertNotNull(this.mockRestServiceServer);

        RestTemplate restTemplate = this.restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

        this.mockRestServiceServer
                .expect(ExpectedCount.once(), requestTo("https://www.ing.nl/api/locator/atms/"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://www.ing.nl/api/locator/atms/", String.class);
        this.mockRestServiceServer.verify();
        assertNotNull(responseEntity);
    }
}