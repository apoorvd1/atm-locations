package com.adviqo.atm.locations.controller;

import com.adviqo.atm.locations.model.Locations;
import com.adviqo.atm.locations.service.LocationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api")
public class AtmLocationsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtmLocationsController.class);

    private LocationService locationService;

    @Autowired
    public AtmLocationsController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ApiOperation(value = "Returns the list of ATM Locations")
    @RequestMapping(method = RequestMethod.GET, path = "/v1/locations")
    @ResponseStatus(OK)
    public List<Locations> findLocation() {
        LOGGER.info("[START] call location controller ");
        return locationService.findLocation();
    }
}
