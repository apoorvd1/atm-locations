package com.adviqo.atm.locations.service

import com.adviqo.atm.locations.AtmLocationApplication
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

@SpringBootTest(classes = AtmLocationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LocationServiceImplE2ETest extends Specification {
    @Value('${local.server.port}')
    int port

    def "return ok when location is found "() {
        given:
        TestRestTemplate restTemplate = new TestRestTemplate()

        when:
        def response = restTemplate.getForEntity("http://localhost:${port}/atm/api/v1/locations", String)

        then:
        response.statusCode == HttpStatus.OK
    }

    def "should throw exception for invalid  locations"() {
        given:
        TestRestTemplate restTemplate = new TestRestTemplate()

        when:
        def response = restTemplate.getForEntity("http://localhost:${port}/atm/api/v2/locations", String)

        then:
        response.statusCode == HttpStatus.NOT_FOUND
    }
}