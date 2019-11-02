package com.adviqo.atm.locations;

import org.junit.Test;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertEquals;

public class AtmLocationSwaggerConfigurationTest {

    @Test
    public void testApiInfo() {
        AtmLocationSwaggerConfiguration configuration = new AtmLocationSwaggerConfiguration();
        ApiInfo result = configuration.apiInfo();
        assertEquals("ATM Location Services", result.getTitle());
        assertEquals(DocumentationType.SWAGGER_2, configuration.newsApi().getDocumentationType());
    }

    @Test
    public void testDocketInfo() {
        AtmLocationSwaggerConfiguration configuration = new AtmLocationSwaggerConfiguration();
        Docket result = configuration.newsApi();
        assertEquals(DocumentationType.SWAGGER_2.getName(), result.getDocumentationType().getName());
        assertEquals(DocumentationType.SWAGGER_2, configuration.newsApi().getDocumentationType());
    }


}