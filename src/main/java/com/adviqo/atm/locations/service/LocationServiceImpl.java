package com.adviqo.atm.locations.service;

import com.adviqo.atm.locations.error.RestTemplateResponseErrorHandler;
import com.adviqo.atm.locations.exception.AtmLocationRuntimeException;
import com.adviqo.atm.locations.model.Locations;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;


@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

    private RestTemplate restTemplate;

    @Value("${ingLocatorHost}")
    private String ingLocatorHost;

    @Autowired
    public LocationServiceImpl(
            RestTemplateBuilder restTemplateBuilder,
            RestTemplate restTemplate) {
        restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()
        ).build();
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Locations> findLocation() {
        LOGGER.info("[START] find location service ");
        try {
            List<Locations> locations = callAtmLocators();
            LOGGER.info("[END] find location service ");
            return locations;

        } catch (AtmLocationRuntimeException e) {
            throw new AtmLocationRuntimeException("Error Occur While Accessing Locations");
        }
    }

    /**
     * Call External Service to Get ATM Locators.
     *
     * @return List<Locations></Locations>
     * @throws AtmLocationRuntimeException IOException
     */
    private List<Locations> callAtmLocators() throws AtmLocationRuntimeException {
        List<Locations> locations = null;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(ingLocatorHost, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            locations = retrieveATMLocators(responseEntity);
        }
        return locations;
    }

    /**
     * Retrieve ATM Locators.
     *
     * @param responseEntity responseEntity
     * @return List<Locations>
     */
    private List<Locations> retrieveATMLocators(ResponseEntity<String> responseEntity) {
        List<Locations> locations;
        String responseBody = responseEntity.getBody();
        String responseJson = Objects.requireNonNull(responseBody).substring(responseBody.indexOf("[{"));
        Gson gson = new Gson();
        Type type = new TypeToken<List<Locations>>() {
        }.getType();
        locations = gson.fromJson(responseJson, type);
        return locations;
    }
}
