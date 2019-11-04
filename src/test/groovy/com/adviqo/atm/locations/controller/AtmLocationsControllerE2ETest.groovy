package com.adviqo.atm.locations.controller

import com.adviqo.atm.locations.AtmLocationApplication
import com.adviqo.atm.locations.exception.GenericExceptionHandler
import com.adviqo.atm.locations.service.LocationService
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = AtmLocationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AtmLocationsControllerE2ETest extends Specification {

    @Value('${local.server.port}')
    int port

    JsonSlurper slurper

    MockMvc mvcMock


    void setup() {
        slurper = new JsonSlurper()
    }

    def "should retrieve all the locations"() {
        when:
        def locators = slurper.parse(new URL("http://localhost:${port}/atm/api/v1/locations"))

        then:
        locators != null
        locators.size == 1478
    }

    def "Should return an not found error when an unhandled exception is thrown"() {
        given:
        def locationService = Mock(LocationService)
        AtmLocationsController controller = new AtmLocationsController(locationService)

        mvcMock = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GenericExceptionHandler())
                .build()

        when:
        def response = mvcMock.perform(MockMvcRequestBuilders.get("/api/v2/locations"))

        then:
        response.andExpect(status().isNotFound())
    }

    def "Should return an internal server error when an unhandled exception is thrown"() {
        given:
        def locationService = Mock(LocationService) {
            _ >> { throw new RuntimeException() }
        }
        AtmLocationsController controller = new AtmLocationsController(locationService)

        mvcMock = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GenericExceptionHandler())
                .build()

        when:
        def response = mvcMock.perform(MockMvcRequestBuilders.get("/api/v1/locations"))

        then:
        response.andExpect(status().isInternalServerError())
    }
}
